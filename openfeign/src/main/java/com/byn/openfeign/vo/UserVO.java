package com.byn.openfeign.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:59`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("用户信息VO")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "emailFlag")
    private String emailFlag;
    @ApiModelProperty(value = "手机")
    private String phone;
    private String phoneFlag;
    @ApiModelProperty(value = "学号")
    private String studentID;
    @ApiModelProperty(value = "学校")
    private String school;
    @ApiModelProperty(value = "介绍")
    private String introduction;
    @ApiModelProperty(value = "头像文件id")
    private String thumb;
    @ApiModelProperty(value = "最后登录时间")
    private Date timeLogin;
    @ApiModelProperty(value = "创建时间")
    private Date timeCreate;
    @ApiModelProperty(value = "更改时间")
    private Date timeUpdate;



}
