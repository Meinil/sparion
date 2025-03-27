package com.meinil.resource.properties;

import com.meinil.resource.service.IFileStorageService;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    /**
     * minio存储
     */
    private MinioStorage minio;

    /**
     * 本地存储
     */
    private LocalStorage local;


    public static class MinioStorage {
        /**
         * 接入点
         */
        private String endpoint;

        private String accessKey;

        private String secretKey;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

    public static class LocalStorage {
        /**
         * 存储路径
         */
        private String dir;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }

    public MinioStorage getMinio() {
        return minio;
    }

    public void setMinio(MinioStorage minio) {
        this.minio = minio;
    }

    public LocalStorage getLocal() {
        return local;
    }

    public void setLocal(LocalStorage local) {
        this.local = local;
    }
}
