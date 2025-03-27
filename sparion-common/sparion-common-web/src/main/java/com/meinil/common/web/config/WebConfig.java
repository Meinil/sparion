package com.meinil.common.web.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import com.meinil.common.web.interceptor.GatewayInterceptor;
import com.meinil.common.web.interceptor.PermissionInterceptor;
import com.meinil.common.web.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    private final static Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new GatewayInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/feign/**", "/error");

        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login", "/auth/register", "/auth/captcha", "/feign/system/user", "/feign/system/register", "/error");

        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 Fastjson2 的 HttpMessageConverter
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        // 配置 Fastjson2
        FastJsonConfig config = new FastJsonConfig();
        // 设置字符集
        config.setCharset(StandardCharsets.UTF_8);

        // 注册自定义的序列化器和反序列化器
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
        converter.setFastJsonConfig(config);

        // 设置支持的 MediaType
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 将 Fastjson2 的 Converter 添加到 Spring 的 Converter 列表中，并放在第一位
        converters.add(0, converter);
        log.info("Fastjson2 install success");
    }
}
