package com.byn.openfeign.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:59`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("修改用户信息fo")
public class UserFO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "性别")
    @Pattern(regexp = "^[0-1]{0,1}", message = "性别输入不正确")
    private String sex;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "email")
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



}
