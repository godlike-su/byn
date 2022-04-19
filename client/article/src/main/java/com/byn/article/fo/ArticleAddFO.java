package com.byn.article.fo;

import com.pagination.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @version v1.0.0.0
 * @Date `2022/4/12 17:23`
 */
@Data
@ApiModel(value = "文章新增FO", description = "文章新增FO")
public class ArticleAddFO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "内容", required = true)
    private String content;

    @ApiModelProperty(value = "是否为草稿 0: 否  1：是")
    @Pattern(regexp = "^\\[0-1]{1}$", message = "请输入0,1")
    private String draft;

    @ApiModelProperty(value = "图片id，使用逗号分隔")
    private String attachGroupId;

}