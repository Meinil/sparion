package com.meinil.sparion.common.utils.file;

import com.meinil.sparion.common.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
public interface Files {
    /**
     * 文件上传
     * @param multipartFile 文件
     * @return 文件信息
     */
    FileInfo upload(MultipartFile multipartFile);

    /**
     * 批量文件上传
     * @param multipartFiles 文件
     * @return 文件信息
     */
    List<FileInfo> upload(MultipartFile[] multipartFiles);

    /**
     * 删除
     * @param fileId 文件id
     * @return 是否删除成功
     */
    Boolean delete(Long fileId);
}
