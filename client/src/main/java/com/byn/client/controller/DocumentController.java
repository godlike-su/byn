package com.byn.client.controller;

import com.byn.client.service.DocumentService;
import com.byn.openfeign.commonweb.fo.DownloadFileFo;
import com.result.BaseResult;
import com.result.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/20 14:52`
 * @Version: 1.0
 * @Description:
 */
@RestController
@Api(value = "文件操作", tags = "文件操作")
@RequestMapping("${byn.mapping.name}${byn.mapping.prefix}/document")
@Slf4j
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "used", value = "文件使用用途 0:头像  1:文章", required = true)
    })
    public MessageResult uploadFileType(@RequestParam("file") MultipartFile file, @RequestParam String used) {
        return documentService.uploadFileType(file, used);
    }

    @GetMapping("/download")
    @ApiOperation(value = "文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attachGroupId", value = "下载文件的groupId", required = true)
    })
    public void downloadFile(@RequestParam String attachGroupId
            , HttpServletRequest request, HttpServletResponse response) {
        documentService.downloadFile(attachGroupId, request, response);
    }

    @PostMapping("/clear")
    @ApiOperation(value = "删除文件")
    public BaseResult cleardFile(@RequestBody @Validated DownloadFileFo fo) {
        return documentService.cleardFile(fo);
    }
}
