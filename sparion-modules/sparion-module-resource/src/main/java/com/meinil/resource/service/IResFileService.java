package com.meinil.resource.service;

import com.meinil.common.web.enums.FileStorageModelEnum;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.resource.domain.vo.ResFileVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
public interface IResFileService {

    /**
     * 文件上传
     * @param file 上传的文件
     * @param storageModel 存储方式 本地存储 Minio存储
     * @param storageType 存储类型 永久存储 临时存储
     * @return 文件id
     */
    Long upload(MultipartFile file, FileStorageModelEnum storageModel, FileStorageTypeEnum storageType);

    /**
     * 文件下载
     * @param fileId 文件id
     */
    void download(Long fileId, HttpServletResponse response);

    /**
     * 根据id集合查询文件元数据
     * @param ids 文件id集合
     * @return 文件元数据列表
     */
    List<ResFileVO> listByIds(List<Long> ids);
}
