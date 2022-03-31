package com.byn.web.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 20:44`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("文件上传fo")
public class UploadFileFo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件使用用途 0:头像  1:文章", required = true)
    @Pattern(regexp = "^\\[0-1]{1}$", message = "请输入0,1")
    @NotBlank(message = "文件使用用途不能为空")
    private String used;

}
