package com.byn.web.controller;

import com.byn.web.fo.DownloadFileFo;
import com.byn.web.service.DocumentService;
import com.result.BaseResult;
import com.result.MessageResult;
import com.result.ReturnStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 14:12`
 * @Version: 1.0
 * @Description:
 */
@RestController
@Api(value = "文件操作", tags = "文件操作")
@RequestMapping("${byn.mapping.name}/web/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

//    @PostMapping("/uploadFile")
//    @ApiOperation(value = "文件上传,默认路径")
    public MessageResult uploadFile(MultipartFile file) {
        String attachGroupId = documentService.uploadFile(file);
        return new MessageResult(attachGroupId);
    }

    @PostMapping("/uploadFileType")
    @ApiOperation(value = "文件上传,设置图片用途")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "used", value = "文件使用用途 0:头像  1:文章", required = true)
    })
    public MessageResult uploadFileType(@RequestParam("file") MultipartFile file
            , @RequestParam String used) {
        String attachGroupId = documentService.uploadFile(file, used);
        return new MessageResult(attachGroupId);
    }

    @GetMapping("/downloadFile")
    @ApiOperation(value = "文件下载")
    public void downloadFile(@RequestParam("attachGroupId") String attachGroupId
            , HttpServletResponse response) {
        documentService.downloadFile(attachGroupId, response);
    }

    @PostMapping("/clearFile")
    @ApiOperation(value = "删除文件")
    public BaseResult cleardFile(@RequestBody @Validated DownloadFileFo fo) {
        documentService.cleardFile(fo);
        return new BaseResult(ReturnStatus.STATUS_OK, "删除文件成功");
    }

}
