package com.meinil.common.web.enums;

import com.meinil.common.web.constants.FileConstant;
import com.meinil.common.web.exception.SparionException;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description 存储方式(本地存储、Minio存储)
 */
public enum FileStorageModelEnum {

    /**
     * 本地存储
     */
    FILE_STORAGE_LOCAL(FileConstant.FILE_STORAGE_MODEL_LOCAL, "localStorageServiceImpl"),

    /**
     * Minio存储
     */
    FILE_STORAGE_MINIO(FileConstant.FILE_STORAGE_MODEL_MINIO, "minioStorageServiceImpl");

    /**
     * 存储类型编码
     */
    private final String code;

    /**
     * 存储
     */
    private final String beanName;

    FileStorageModelEnum(String code, String beanName) {
        this.code = code;
        this.beanName = beanName;
    }

    public String getCode() {
        return code;
    }

    public String getBeanName() {
        return beanName;
    }

    public static FileStorageModelEnum value(String code) {
        for (FileStorageModelEnum value : FileStorageModelEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        throw new SparionException("未匹配到code为: %s的FileStorageModelEnum枚举值", code);
    }
}
