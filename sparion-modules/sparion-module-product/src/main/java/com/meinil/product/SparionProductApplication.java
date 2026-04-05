package com.meinil.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author meinil
 * @date 2026/3/28
 * @description TODO
 */
@SpringBootApplication
@MapperScan(basePackages = "com.meinil.product.mapper")
public class SparionProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionProductApplication.class, args);
    }

}
