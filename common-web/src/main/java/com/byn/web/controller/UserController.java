package com.byn.web.controller;

import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.session.service.SessionUser;
import com.byn.web.fo.UserFO;
import com.byn.web.service.UserService;
import com.byn.web.vo.UserVO;
import com.result.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0.0
 * @Date `2022/4/5 11:04`
 */
@RestController
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("${byn.mapping.name}/web/user")
public class UserController {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private UserService userService;

    @PostMapping("/getMyInformation")
    @ApiOperation(value = "获取自己的信息")
    public SingleResult<UserVO> getMyInformation() {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        UserVO myInformation = userService.getMyInformation(sessionUser);
        return new SingleResult<>(myInformation);
    }

    @PostMapping("/getUserInformation")
    @ApiOperation(value = "获取其他的用户信息信息")
    public SingleResult<UserVO> getUserInformation(@RequestBody UserFO userFO) {
        UserVO myInformation = userService.getUserInformation(userFO);
        return new SingleResult<>(myInformation);
    }


}
