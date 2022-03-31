package com.byn.client.service.impl;

import com.byn.client.service.DocumentService;
import com.byn.common.util.UrlParse;
import com.byn.openfeign.commonweb.feign.CommonWebFeign;
import com.byn.openfeign.commonweb.fo.DownloadFileFo;
import com.result.BaseResult;
import com.result.MessageResult;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/28 0:15`
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    @Resource
    private CommonWebFeign commonWebFeign;

    @Override
    public MessageResult uploadFileType(MultipartFile file, String used) {
        return commonWebFeign.uploadFileType(file, used);
    }

    @Override
    public void downloadFile(String attachGroupId, HttpServletRequest request, HttpServletResponse response) {
        Response response1 = commonWebFeign.downloadFile(attachGroupId, response);
        Map<String, Collection<String>> headers = response1.headers();
        Collection<String> strings = headers.get("content-disposition");
        Iterator<String> iterator = strings.iterator();
        String fileName = "";
        while (iterator.hasNext()) {
            fileName = iterator.next();
        }
        response.setHeader("Content-Disposition", fileName);
        UrlParse urlParse = new UrlParse();
        String suffix = urlParse.urlSuffix(fileName);
        response.setContentType(urlParse.getContentType(suffix));
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");

        try (InputStream in = response1.body().asInputStream();
             OutputStream out = response.getOutputStream()) {
            // 下载文件的命名

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

        } catch (Exception e) {
            log.error("文件下载错误", e);
        }

    }

    @Override
    public BaseResult cleardFile(DownloadFileFo fo) {
        return commonWebFeign.cleardFile(fo);
    }
}
