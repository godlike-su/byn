package com.byn.openfeign.commonweb.feign;

import com.byn.openfeign.commonweb.fo.DownloadFileFo;
import com.byn.openfeign.commonweb.fo.User;
import com.byn.openfeign.commonweb.fo.WXloginFo;
import com.result.BaseResult;
import com.result.MessageResult;
import feign.Param;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/20 2:30`
 * @Version: 1.0
 * @Description:
 */
@Component
@FeignClient(name="byn-web-common")
public interface CommonWebFeign {
    /**
     * 微信登录
     * @param fo
     * @return
     */
    @PostMapping("${byn.mapping.name}/web/common/wxLogin")
    MessageResult wxLogin(@RequestBody @Validated WXloginFo fo);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("${byn.mapping.name}/web/common/saveUser")
    BaseResult saveUser(User user);

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link String}
     */
    @PostMapping(value = "${byn.mapping.name}/web/common/uploadFileType"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    MessageResult uploadFileType(@Param("file") MultipartFile file, @RequestParam("used") String used);

    /**
     * 下载文件
     *
     * @param attachGroupId
     * @param response
     */
    @GetMapping(value = "${byn.mapping.name}/web/common/downloadFile")
    Response downloadFile(@RequestParam("attachGroupId") String attachGroupId
            , @RequestParam("response") HttpServletResponse response);


    /**
     * 清理文件
     *
     * @param fo
     * @return int
     */
    @PostMapping("${byn.mapping.name}/web/common/clearFile")
    BaseResult cleardFile(@RequestBody @Validated DownloadFileFo fo);

}
