package com.byn.web.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:55`
 * @Version: 1.0
 * @Description: 文件上传表
 */
@Data
public class FileUpload {

    /**
     * id
     */
    private String id;

    /**
     * 原文件名称
     */
    private String name;

    /**
     * 保存地址
     */
    private String url;

    /**
     * 放入时间
     */
    private Date createTime;

    /**
     * 文件后缀
     */
    private String profilx;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件id
     */
    private String attachGroupId;

    /**
     * 文件用途 0: 头像，1:文章;
     */
    private String used;
}
