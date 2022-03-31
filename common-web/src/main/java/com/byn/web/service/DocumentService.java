package com.byn.web.service;

import com.byn.web.fo.DownloadFileFo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 14:19`
 * @Version: 1.0
 * @Description:
 */
public interface DocumentService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link String}
     */
    String uploadFile(MultipartFile file);

    String uploadFile(MultipartFile file, String used);

    /**
     * 下载文件
     *
     * @param attachGroupId
     * @param response
     */
    void downloadFile(String attachGroupId, HttpServletResponse response);


    /**
     * cleard文件
     *
     * @param fo
     * @return int
     */
    int cleardFile(DownloadFileFo fo);

}
