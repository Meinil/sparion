package com.meinil.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = "com.meinil.ai.mapper")
public class SparionAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionAiApplication.class, args);
    }
}
