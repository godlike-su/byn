package com.byn.article.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer articleid;

    private Integer creator;

    private String title;

    private String text;

    private String content;

    private Integer cat1;

    private Date timecreate;

    private Date timeupdate;

    private Integer niceflag;

    private Integer topflag;

    private Integer delflag;

    private String type;

    private String address;

    private Integer form;

    private Integer audit;

    private Integer draft;

    private Integer numreply;

    private Integer numstart;

    private Integer numlike;

    private String attachGroupId;

}
