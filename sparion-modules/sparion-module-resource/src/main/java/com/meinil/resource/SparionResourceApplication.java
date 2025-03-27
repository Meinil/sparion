package com.meinil.resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Meinil
 * @date 2025/3/24
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = "com.meinil.resource.mapper")
public class SparionResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionResourceApplication.class, args);
    }
}
