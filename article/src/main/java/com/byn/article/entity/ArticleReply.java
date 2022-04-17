package com.byn.article.entity;

import java.util.Date;

public class ArticleReply {
    private String articlereplyid;

    private String articleid;

    private String userid;

    private String username;

    private String replyuserid;

    private String replyname;

    private Date replytime;

    private Date updatetime;

    private String delflag;

    private String replycontent;

    public String getArticlereplyid() {
        return articlereplyid;
    }

    public void setArticlereplyid(String articlereplyid) {
        this.articlereplyid = articlereplyid == null ? null : articlereplyid.trim();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getReplyuserid() {
        return replyuserid;
    }

    public void setReplyuserid(String replyuserid) {
        this.replyuserid = replyuserid == null ? null : replyuserid.trim();
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname == null ? null : replyname.trim();
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag == null ? null : delflag.trim();
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent == null ? null : replycontent.trim();
    }
}