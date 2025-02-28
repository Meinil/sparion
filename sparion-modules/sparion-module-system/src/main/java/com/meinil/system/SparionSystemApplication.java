package com.meinil.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = "com.meinil.system.mapper")
public class SparionSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionSystemApplication.class, args);
    }
}
