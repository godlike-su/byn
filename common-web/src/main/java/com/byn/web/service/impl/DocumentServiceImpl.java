package com.byn.web.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.byn.common.exception.DataNullException;
import com.byn.common.exception.GenerallyeException;
import com.byn.common.exception.ParamerException;
import com.byn.web.entity.FileUpload;
import com.byn.web.fo.DownloadFileFo;
import com.byn.web.mapper.FileUploadMapper;
import com.byn.web.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/20 15:29`
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

//    private final String absoluteUrl = "../file/";
    @Value("${file.uploadFolder}")
    private String absoluteUrl;

    @Override
    public String uploadFile(MultipartFile file) {
        return uploadFileUrl(file);
    }

    @Override
    public String uploadFile(MultipartFile file, String used) {
        return uploadFileUrl(file, used);
    }

    @Override
    public void downloadFile(String attachGroupId, HttpServletResponse response) {
        // 根据文件id查找文件信息
        FileUpload fileUpload = fileUploadMapper.selectByAttachGroupId(attachGroupId);
        if (ObjectUtils.isEmpty(fileUpload)) {
            throw new ParamerException("查找不到该文件！");
        }
        File file = new File(absoluteUrl + fileUpload.getUrl() + fileUpload.getProfilx());
        if (!file.exists()) {
            throw new DataNullException("该文件不存在！");
        }

        String filename = filenameEncoding(fileUpload.getName());
        // 告知浏览器文件的大小
        response.addHeader("Content-Length", "" + file.length());
        // 强制打不开，下载
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("下载文件出错:", e);
            throw new GenerallyeException("下载文件出错!");
        }
    }

    @Override
    public int cleardFile(DownloadFileFo fo) {
        // 根据文件id查找文件信息
        FileUpload fileUpload = fileUploadMapper.selectByAttachGroupId(fo.getAttachGroupId());
        if (ObjectUtils.isEmpty(fileUpload)) {
            log.error("查找不到该删除文件信息");
            return 0;
        }
        return deleteFile(fileUpload);
    }


    /**
     * 删除文件
     *
     * @return int
     */
    private int deleteFile(FileUpload fileDetail) {
        File file = new File(absoluteUrl + fileDetail.getUrl() + fileDetail.getProfilx());
        Path path = Paths.get(file.getPath());
        try {
            if (file.exists()) {
                Files.delete(path);
            }
        } catch (IOException e) {
            log.error("该文件路径不存在: {}", path.toString());
        }

        // 数据库也需要清理
        return fileUploadMapper.deleteByAttachGroupId(fileDetail.getAttachGroupId());
    }

    /**
     * 上传文件url
     *
     * @param used 文件使用方式
     * @return {@link String}
     */
    private String uploadFileUrl(MultipartFile file, String used) {
        String format = DateUtil.format(new Date(), "yyyyMMdd");
        String filePath = format + "/";
        switch (used) {
            case "1":
                filePath = "thumb/" + format + "/";
                break;
            case "2":
                filePath = "analysis/" + format + "/";
                break;
            case "3":
                filePath = "other/" + format + "/";
                break;
        }
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        // 获取文件类型
        String mimeType = file.getContentType();
        // 获取文件后缀
        String fileName;
        String profilx = "";
        try {
            fileName = file.getOriginalFilename();
            // 获取文件名后缀 .jpg .png
            int i = fileName.lastIndexOf(".");
            if (i != -1) {
                profilx = fileName.substring(i);
            }
        } catch (NullPointerException e) {
            throw new ParamerException("文件名获取错误!");
        }

        // 获取随机id
        String simpleUUID = IdUtil.simpleUUID();

        File fileUpload = new File(absoluteUrl + filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        File dest = new File(absoluteUrl + filePath, simpleUUID + profilx);

        try {
            FileUpload fileInformation = new FileUpload();
            fileInformation.setUrl(filePath + simpleUUID);
            fileInformation.setName(fileName);
            fileInformation.setAttachGroupId(simpleUUID);
            fileInformation.setCreateTime(new Date());
            fileInformation.setProfilx(profilx);
            fileInformation.setType(mimeType);
            // 先存入数据库
            fileUploadMapper.addFile(fileInformation);
            // 下载文件
            file.transferTo(dest);
            return fileInformation.getAttachGroupId();
        } catch (IOException e) {
            log.error(e.toString(), e);
            throw new GenerallyeException("文件上传错误");
        }
    }

    /**
     * 上传文件url
     *
     * @param file 文件
     * @return {@link String}
     */
    private String uploadFileUrl(MultipartFile file) {
        return uploadFileUrl(file, "0");
    }

    /**
     * 文件名解析
     *
     * @param fileName 文件名
     * @return {@link String}
     */
    public String filenameEncoding(String fileName) {
        try {
            return URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new GenerallyeException("解析文件名出错!");
        }

    }
}
