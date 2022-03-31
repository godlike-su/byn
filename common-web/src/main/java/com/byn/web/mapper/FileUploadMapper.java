package com.byn.web.mapper;

import com.byn.web.entity.FileUpload;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 15:16`
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface FileUploadMapper {

    /**
     * 添加文件
     *
     * @param fileUpload 文件上传
     * @return int
     */
    int addFile(FileUpload fileUpload);

    FileUpload selectByAttachGroupId(String attachGroupId);

    int deleteByAttachGroupId(String attachGroupId);


}
