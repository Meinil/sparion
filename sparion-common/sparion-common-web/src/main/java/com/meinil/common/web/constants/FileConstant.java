package com.meinil.common.web.constants;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description 文件常量类
 */
public class FileConstant {

    private FileConstant() {}

    /**
     * 文件存储方式 本地存储
     */
    public final static String FILE_STORAGE_MODEL_LOCAL = "1";

    /**
     * 文件存储方式 Minio存储
     */
    public final static String FILE_STORAGE_MODEL_MINIO = "2";

    /**
     * 文件存储类型 1-永久
     */
    public final static String FILE_STORAGE_TYPE_PERMANENT = "1";

    /**
     * 文件存储类型 1-临时
     */
    public final static String FILE_STORAGE_TYPE_TEMP = "2";

    /**
     * 永久存储桶
     */
    public final static String BUCKET_PERMANENT = "sparion.permanent";

    /**
     * 临时存储桶(可随时删除)
     */
    public final static String BUCKET_TEMP = "sparion.temp";

    /**
     * 所有的存储桶
     */
    public final static List<String> MINIO_BUCKETS = List.of(BUCKET_PERMANENT, BUCKET_TEMP);
}
