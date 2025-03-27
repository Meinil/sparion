package com.meinil.resource.service.impl;

import com.meinil.common.web.constants.FileConstant;
import com.meinil.common.web.enums.FileStorageTypeEnum;
import com.meinil.common.web.exception.SparionException;
import com.meinil.resource.properties.StorageProperties;
import com.meinil.resource.service.IFileStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@Service
@ConditionalOnProperty(name = "storage.local.dir")
public class LocalStorageServiceImpl implements IFileStorageService {

    private final StorageProperties storageProperties;

    public LocalStorageServiceImpl(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void init() {
        String dir = storageProperties.getLocal().getDir();
        File file = new File(dir);
        if (file.exists()) {
            if (file.isFile()) {
                throw new SparionException("%s不是一个目录", dir);
            }
        } else {
            for (String bucket : FileConstant.MINIO_BUCKETS) {
                new File(dir + File.separator + bucket).mkdirs();
            }
        }
    }

    @Override
    public void upload(InputStream inputStream, Long fileSize, String path, FileStorageTypeEnum storageType) {
        File file = new File(storageProperties.getLocal().getDir() + File.separator + storageType.getBucketName() + File.separator + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(inputStream.readAllBytes());
        } catch (Exception e) {
            throw new SparionException(e);
        }
    }

    @Override
    public InputStream download(String path, FileStorageTypeEnum storageType) {
        String bucketName = storageType.getBucketName();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(storageProperties.getLocal().getDir() + File.separator +bucketName + File.separator + path);
        } catch (FileNotFoundException e) {
            throw new SparionException("文件未找到");
        }
        return fileInputStream;
    }
}
