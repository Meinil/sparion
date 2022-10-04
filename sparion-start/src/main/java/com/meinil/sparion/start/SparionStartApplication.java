package com.meinil.sparion.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.meinil.sparion")
@MapperScan(basePackages = { "com.meinil.sparion.common.mapper",  "com.meinil.sparion.bot.mapper"})
public class SparionStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparionStartApplication.class, args);
    }
}
