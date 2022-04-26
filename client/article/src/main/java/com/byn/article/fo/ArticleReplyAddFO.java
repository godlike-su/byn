package com.byn.article.fo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ApiModel(value = "文章回复新增FO", description = "文章回复新增FO")
public class ArticleReplyAddFO {
    @ApiModelProperty(value = "回复的文章id", required = true)
    @NotBlank
    private String articleid;

    @ApiModelProperty(value = "回复的用户id")
    private String replyuserid;

    @ApiModelProperty(value = "回复的用户名称")
    private String replyname;

    @ApiModelProperty(value = "回复的内容", required = true)
    @NotBlank
    private String replycontent;

}