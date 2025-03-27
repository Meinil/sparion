package com.meinil.resource.config;

import com.meinil.resource.properties.StorageProperties;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@Configuration
@EnableConfigurationProperties({ StorageProperties.class })
public class StorageConfig {

    private final StorageProperties storageProperties;

    public StorageConfig(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "storage.minio.endpoint")
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(storageProperties.getMinio().getEndpoint())
                .credentials(storageProperties.getMinio().getAccessKey(), storageProperties.getMinio().getSecretKey())
                .build();
    }
}
