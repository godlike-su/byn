package com.byn.web.service;

import com.byn.common.session.entity.SessionUserDetail;
import com.byn.web.entity.User;
import com.byn.web.fo.LoginFo;
import com.byn.web.fo.UserFO;
import com.byn.web.fo.WXloginFo;
import com.result.MessageResult;
import com.result.SingleResult;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 16:04`
 * @Version: 1.0
 * @Description:
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginFo 登录佛
     * @return {@link String}
     */
    String login(LoginFo loginFo);

    /**
     * 微信登录
     *
     * @param wXloginFo 登录佛
     * @return {@link String}
     */
    SingleResult wxLogin(WXloginFo wXloginFo);

    /**
     * 修改用户信息
     * @param user
     * @param sessionUser
     * @return
     */
    int saveUser(UserFO user, SessionUserDetail sessionUser);
}
