package com.byn.web.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 20:44`
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("文件下载fo")
public class DownloadFileFo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "下载文件的groupId", required = true)
//    @Pattern(regexp = "^$|^\\d{4}-\\d{2}-\\d{2}", message = "时间不正确")
    @NotBlank(message = "文件id不能为空")
    private String attachGroupId;

}
