package com.byn.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章回复表
 * </p>
 *
 * @author 
 * @since 2022-04-18
 */
@Data
@ApiModel(value="ArticleReply对象", description="文章回复表")
public class ArticleReplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复表id")
    private String articlereplyid;

    @ApiModelProperty(value = "文章id")
    private String articleid;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "回复人id")
    private String replyuserid;

    @ApiModelProperty(value = "回复人姓名")
    private String replyname;

    @ApiModelProperty(value = "回复内容")
    private String replycontent;

    @ApiModelProperty(value = "回复时间")
    private Date replytime;

    @ApiModelProperty(value = "修改时间")
    private Date updatetime;

    @ApiModelProperty(value = "删除标识 1:删除")
    private String delflag;


}
