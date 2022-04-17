package com.byn.article.vo;

import com.byn.article.entity.ArticleReply;
import com.pagination.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Data
public class ArticleReplyVO extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String articleid;

    private String creator;

    private String title;

    private String text;

    private String content;

    private String cat1;

    private Date timecreate;

    private Date timeupdate;

    private String niceflag;

    private String topflag;

    private String delflag;

    private String type;

    private String address;

    private String form;

    private String audit;

    private String draft;

    private String numreply;

    private String numstart;

    private String numlike;

    private String attachGroupId;

    private List<ArticleReply> articleReplyList;

}
