package com.meinil.sparion.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                      // 允许跨域的路径
                .allowedOriginPatterns("*")                       // 允许跨域请求的域名
                .allowCredentials(true)                           // 是否允许cookie
                .allowedMethods("GET", "POST", "PUT", "DELETE")   // 允许跨域的请求方式
                .allowedHeaders("*")                              // 允许跨域的header
                .maxAge(3600);                                    // 跨域允许时间
    }
}
