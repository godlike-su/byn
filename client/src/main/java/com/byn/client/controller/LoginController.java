package com.byn.client.controller;

import com.byn.client.fo.UserFo;
import com.byn.client.service.LoginService;
import com.byn.openfeign.commonweb.fo.WXloginFo;
import com.result.BaseResult;
import com.result.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/20 2:39`
 * @Version: 1.0
 * @Description:
 */
@RestController
@Api(value = "登录、注销", tags = "登录")
@RequestMapping("${byn.mapping.name}${byn.mapping.prefix}/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录")
    public MessageResult wxLogin(@RequestBody @Validated WXloginFo fo) {
        return loginService.wxLogin(fo);
    }

    @PostMapping("/saveUser")
    @ApiOperation(value = "修改自己的信息")
    public BaseResult saveUser(@RequestBody @Validated UserFo fo) {
        return loginService.saveUser(fo);
    }

}
