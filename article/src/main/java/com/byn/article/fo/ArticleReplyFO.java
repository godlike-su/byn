package com.byn.article.fo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleReplyFO {
    private String articlereplyid;

    private String articleid;

    private String userid;

    private String username;

    private String replyuserid;

    private String replyname;

    private Date replytime;

    private Date updatetime;

    private String replycontent;

}