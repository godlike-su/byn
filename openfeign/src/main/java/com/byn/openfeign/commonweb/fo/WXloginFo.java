package com.byn.openfeign.commonweb.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/4 19:33`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("微信登录fo")
public class WXloginFo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信返回的code", required = true)
    @NotBlank
    private String jsCode;
}
