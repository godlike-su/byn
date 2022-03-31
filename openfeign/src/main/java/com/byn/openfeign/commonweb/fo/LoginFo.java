package com.byn.openfeign.commonweb.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:59`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("登录fo")
public class LoginFo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;

}
