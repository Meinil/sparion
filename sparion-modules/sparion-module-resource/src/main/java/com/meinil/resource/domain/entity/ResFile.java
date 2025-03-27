package com.meinil.resource.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.common.mybatis.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description 文件实体类
 */
@TableName("res_file")
public class ResFile extends BaseEntity {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 原始文件名
     */
    private String originalName;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 存储方式 1-本地 2-minio
     */
    private String storageModel;

    /**
     * 存储类型 1-永久 2-临时
     */
    private String storageType;

    /**
     * 文件hash值
     */
    private String hash;

    /**
     * 文件大小
     */
    private Long fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStorageModel() {
        return storageModel;
    }

    public void setStorageModel(String storageModel) {
        this.storageModel = storageModel;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
