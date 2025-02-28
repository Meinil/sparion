package com.meinil.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
@EnableFeignClients(basePackages = "com.meinil.*.api.feign")
@SpringBootApplication
public class SparionAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionAuthApplication.class, args);
    }
}
