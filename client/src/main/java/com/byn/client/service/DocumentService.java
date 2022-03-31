package com.byn.client.service;

import com.byn.openfeign.commonweb.fo.DownloadFileFo;
import com.result.BaseResult;
import com.result.MessageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/28 0:15`
 * @Version: 1.0
 * @Description:
 */
public interface DocumentService {
    /**
     * 上传文件
     * @param file
     * @param used
     * @return
     */
    MessageResult uploadFileType(MultipartFile file, String used);

    /**
     * 下载文件
     * @param attachGroupId
     * @param request
     * @param response
     */
    void downloadFile(String attachGroupId
            , HttpServletRequest request, HttpServletResponse response);


    /**
     * 删除文件
     * @param fo
     * @return
     */
    BaseResult cleardFile(@RequestBody @Validated DownloadFileFo fo);
}
