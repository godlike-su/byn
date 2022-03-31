package com.byn.client.service.impl;

import com.byn.client.fo.UserFo;
import com.byn.client.service.LoginService;
import com.byn.common.util.ObjectTransform;
import com.byn.openfeign.commonweb.feign.CommonWebFeign;
import com.byn.openfeign.commonweb.fo.User;
import com.byn.openfeign.commonweb.fo.WXloginFo;
import com.result.BaseResult;
import com.result.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/28 0:11`
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private CommonWebFeign commonWebFeign;

    @Override
    public MessageResult wxLogin(WXloginFo fo) {
        return commonWebFeign.wxLogin(fo);
    }

    @Override
    public BaseResult saveUser(UserFo fo) {
        User user = ObjectTransform.transform(fo, User.class);
        if (StringUtils.isBlank(user.getSex())) {

        }
        return commonWebFeign.saveUser(user);
    }
}
