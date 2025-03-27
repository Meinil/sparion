package com.meinil.resource.service.impl;

import com.meinil.common.web.constants.FileConstant;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.common.web.exception.SparionException;
import com.meinil.resource.service.IFileStorageService;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@Primary
@Service
@ConditionalOnBean(MinioClient.class)
public class MinioStorageServiceImpl implements IFileStorageService {

    private final MinioClient minioClient;

    public MinioStorageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void init() {
        try {
            for (String bucket : FileConstant.MINIO_BUCKETS) {
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
                if (!found) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                }
            }
        } catch (Exception e) {
            throw new SparionException(e);
        }
    }

    @Override
    public void upload(InputStream inputStream, Long fileSize, String path, FileStorageTypeEnum storageType)  {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(storageType.getBucketName())
                            .object(path)
                            .stream(inputStream, fileSize, -1)
                            .build()
            );
        } catch (Exception e) {
            throw new SparionException(e);
        }
    }

    @Override
    public InputStream download(String path, FileStorageTypeEnum storageType) {
        try {
             return minioClient.getObject(
                     GetObjectArgs.builder()
                        .bucket(storageType.getBucketName())
                        .object(path)
                        .build()
             );
        } catch (Exception e) {
            throw new SparionException(e);
        }
    }
}
