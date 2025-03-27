package com.meinil.resource.service;


import com.meinil.common.web.enums.FileStorageTypeEnum;

import java.io.InputStream;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
public interface IFileStorageService {

    /**
     * 初始化 项目启动时执行
     */
    void init();

    /**
     * 文件上传
     * @param inputStream 文件流
     * @param fileSize 文件大小
     * @param path 文件存储路径
     * @param storageType 文件存储类型
     */
    void upload(InputStream inputStream, Long fileSize, String path, FileStorageTypeEnum storageType);

    /**
     * 文件下载
     * @param path 文件存储路径
     * @param storageType 存储类型
     */
    InputStream download(String path, FileStorageTypeEnum storageType);
}
