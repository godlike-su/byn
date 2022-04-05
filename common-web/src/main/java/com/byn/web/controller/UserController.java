package com.byn.web.controller;

import io.swagger.annotations.Api;
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



}
