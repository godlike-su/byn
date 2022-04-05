package com.byn.article.fo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/28 0:09`
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserFo {

    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "性别 0: 男  1: 女")
    @Pattern(regexp = "^[0-1]{0,1}", message = "性别输入不正确")
    private String sex;
    @ApiModelProperty(value = "学号")
    private String studentID;
    @ApiModelProperty(value = "学校")
    private String school;
    @ApiModelProperty(value = "介绍")
    private String introduction;
    @ApiModelProperty(value = "头像文件id")
    private String thumb;
}
