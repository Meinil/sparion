package com.meinil.resource.init;

import com.meinil.resource.service.IFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description 文件初始化
 */
@Component
public class FileInitializer implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(FileInitializer.class);

    private final Map<String, IFileStorageService> fileStorageServiceMap;

    public FileInitializer(Map<String, IFileStorageService> fileStorageServiceMap) {
        this.fileStorageServiceMap = fileStorageServiceMap;
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorageServiceMap.forEach((key, value) -> {
            log.info("初始化: {}", key);
            value.init();
            log.info("初始化完成: {}", key);
        });
    }
}
