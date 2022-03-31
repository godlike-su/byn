package com.byn.web.controller;

import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.session.service.SessionUser;
import com.byn.web.entity.User;
import com.byn.web.fo.LoginFo;
import com.byn.web.fo.WXloginFo;
import com.byn.web.service.LoginService;
import com.result.BaseResult;
import com.result.MessageResult;
import com.result.ReturnStatus;
import com.result.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:57`
 * @Version: 1.0
 * @Description:
 */
@RestController
@Api(value = "登录、注销", tags = "登录")
@RequestMapping("${byn.mapping.name}${byn.mapping.prefix}")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionUser sessionUser;


    @Autowired
    private StringEncryptor stringEncryptor;


    @PostMapping("/login")
    @ApiOperation(value = "普通登录")
    public MessageResult login(@RequestBody @Validated LoginFo loginFo) {
        String login = loginService.login(loginFo);
        return new MessageResult(login);
    }

    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录")
    public SingleResult wxLogin(@RequestBody @Validated WXloginFo fo) {
        return loginService.wxLogin(fo);
    }


    @PostMapping("/saveUser")
    @ApiOperation(value = "更改用户信息，不包括密码")
    public BaseResult saveUser(@RequestBody User user) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        loginService.saveUser(user, sessionUser);
        return new BaseResult(ReturnStatus.STATUS_OK);
    }

    @PostMapping("/test")
    public MessageResult enc(@RequestBody String enc) {
        String encrypt = stringEncryptor.encrypt(enc);
        return new MessageResult(encrypt);

    }

    @PostMapping("/test2")
    public MessageResult dec(@RequestBody String enc) {
        String encrypt = stringEncryptor.decrypt(enc);
        return new MessageResult(encrypt);

    }



}
