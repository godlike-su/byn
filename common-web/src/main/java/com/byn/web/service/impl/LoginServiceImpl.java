package com.byn.web.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.byn.common.exception.GenerallyeException;
import com.byn.common.exception.ParamerException;
import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.util.ObjectTransform;
import com.byn.common.util.SnowFlakeUtil;
import com.byn.web.entity.User;
import com.byn.web.fo.LoginFo;
import com.byn.web.fo.UserFO;
import com.byn.web.fo.WXloginFo;
import com.byn.web.mapper.UserMapper;
import com.byn.web.service.LoginService;
import com.byn.web.util.RedisUtil;
import com.result.ReturnStatus;
import com.result.SingleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 16:04`
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.token.key}")
    private String tokenKey;

    @Value("${jwt.token.expired_time_hour}")
    private int expiredTimeHour;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grantType;

    private static final String BYN_AUTHORIZATION = "byn_authorization";
    private static final long TIMEOUT_SECOND = 86400;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String login(LoginFo loginFo) {
        User user = userMapper.loadUserByUsername(loginFo.getUsername());
        if (ObjectUtils.isEmpty(user)) {
            throw new GenerallyeException("用户不存在!");
        }
        // 成功则返回jwt
        // 登录加密, 暂定sha1
        String encode = SecureUtil.sha1(loginFo.getPassword());
        if (!encode.equals(user.getPassword())) {
            throw new GenerallyeException("密码错误！");
        }
        // 生成JWT token
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("userName", user.getUserName());

        return JWTUtil.createToken(map, tokenKey.getBytes());
    }

    @Override
    public SingleResult wxLogin(WXloginFo wXloginFo) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" +
                appid +
                "&secret=" +
                secret +
                "&js_code=" +
                wXloginFo.getJsCode() +
                "&grant_type=" +
                grantType;
        String result = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!ObjectUtils.isEmpty(jsonObject.getString("errcode"))) {
            // 根据appid获取用户信息
            throw new ParamerException("参数出错");
        }
        /** 返回session_key 和 openid ,unionid 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
         *  errcode	number	错误码 errmsg	string	错误信息
         */
        String wxAppid = jsonObject.getString("openid");
        User user = userMapper.loadUserByWxAppid(wxAppid);
        // 查询不到用户
        if(ObjectUtils.isEmpty(user)) {
            // 注册一个用户，只注册用户id
            user = new User();
            user.setWxAppid(wxAppid);
            user.setUserId(String.valueOf(SnowFlakeUtil.getId()));
            user.setTimeCreate(new Date());
            user.setTimeUpdate(new Date());
            user.setTimeLogin(new Date());
            userMapper.register(user);
            User data = new User();
            data.setUserId(user.getUserId());
            return new SingleResult(data, ReturnStatus.STATUS_NOUSER, "用户未注册，跳转注册界面，返回用户id");
        } else if(StringUtils.isEmpty(user.getUserName())) {
            User data = new User();
            data.setUserId(user.getUserId());
            return new SingleResult(data, ReturnStatus.STATUS_NOUSER, "用户未注册，跳转注册界面，返回用户id");
        }
        String sessionKey = jsonObject.getString("session_key");
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.hset(BYN_AUTHORIZATION, sessionKey, TIMEOUT_SECOND);
        return new SingleResult(sessionKey, ReturnStatus.STATUS_OK, "登录成功，已返回token");
    }

    @Override
    public int saveUser(UserFO userFO, SessionUserDetail sessionUser) {
        User user = ObjectTransform.transform(userFO, User.class);
        user.setUserId(sessionUser.getUserId());
        user.setTimeUpdate(new Date());
        return userMapper.saveUser(user);
    }
}
