package com.meinil.common.web.enums;

import com.meinil.common.web.constants.FileConstant;
import com.meinil.common.web.exception.SparionException;

/**
 * @author Meinil
 * @date 2025/3/25
 * @description 存储类型(永久存储、临时存储)
 */
public enum FileStorageTypeEnum {

    /**
     * 永久存储桶
     */
    FILE_STORAGE_PERMANENT(FileConstant.FILE_STORAGE_TYPE_PERMANENT, FileConstant.BUCKET_PERMANENT),

    /**
     * 临时存储桶
     */
    FILE_STORAGE_TEMP(FileConstant.FILE_STORAGE_TYPE_TEMP, FileConstant.BUCKET_TEMP);

    /**
     * 存储桶编码
     */
    private final String code;

    /**
     * 存储桶
     */
    private final String bucketName;

    FileStorageTypeEnum(String code, String bucketName) {
        this.code = code;
        this.bucketName = bucketName;
    }

    public String getCode() {
        return code;
    }

    public String getBucketName() {
        return bucketName;
    }

    public static FileStorageTypeEnum value(String code) {
        for (FileStorageTypeEnum value : FileStorageTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        throw new SparionException("未匹配到code为: %s的FileStorageEnum枚举值", code);
    }
}
