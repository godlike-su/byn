package com.byn.article.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "文章回复新增FO", description = "文章回复新增FO")
public class ArticleReplySaveFO {
    @ApiModelProperty(value = "评论id", required = true)
    @NotBlank
    private String articlereplyid;

}