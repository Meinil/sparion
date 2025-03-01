package com.meinil.common.web.config;

import com.meinil.common.web.interceptor.GatewayInterceptor;
import com.meinil.common.web.interceptor.TokenInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new GatewayInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/feign/**", "/error");

        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login", "/auth/register", "/auth/captcha", "/feign/system/user", "/feign/system/register", "/error");
    }
}
