package com.byn.article.fo;

import com.pagination.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0.0.0
 * @Date `2022/4/12 17:23`
 */
@Data
@ApiModel(value="ArticleFO", description="ArticleFO")
public class ArticleFO extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章id")
    private String articleid;

    @ApiModelProperty(value = "创建者id")
    private String userid;

    @ApiModelProperty(value = "创建者名字")
    private String userName;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章前50个字")
    private String text;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "板块1 0: 动态广场  1:树洞")
    private String cat1;

    @ApiModelProperty(value = "精华文章")
    private String niceflag;

    @ApiModelProperty(value = "置顶文章")
    private String topflag;

    @ApiModelProperty(value = "文章类型 0:原创 1:转载 2:官方推文")
    private String type;

    @ApiModelProperty(value = "转载地址")
    private String address;

    @ApiModelProperty(value = "发布形式 暂时没想到")
    private String form;

    @ApiModelProperty(value = "需要先通过审核 0:待审核  1:审核通过 2:审核不通过")
    private String audit;

    @ApiModelProperty(value = "是否为草稿 0: 否  1：是")
    private String draft;

    @ApiModelProperty(value = "图片id，使用逗号分隔")
    private String attachGroupId;

}