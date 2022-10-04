package com.meinil.sparion.common.config;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author Meinil
 * @date 2022/9/26
 * @description JSON配置
 */
@Configuration
public class JsonMessageConverterConfigurer implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 自定义配置
        FastJsonConfig config = new FastJsonConfig();

        // 默认日期格式
        config.setDateFormat("yyyy/MM/dd");

        config.setWriterFeatures(
                JSONWriter.Feature.FieldBased,                       // 基于字段而不是getter
                JSONWriter.Feature.WriteMapNullValue,                // 保留map空的字段
                JSONWriter.Feature.WriteNullNumberAsZero,            // Number类型null转为0
                JSONWriter.Feature.WriteNullListAsEmpty,             // 空List序列化为[]
                JSONWriter.Feature.WriteNullStringAsEmpty,           // 空字符串序列化为""
                JSONWriter.Feature.WriteNullBooleanAsFalse,          // 将Boolean类型的null转成false
                JSONWriter.Feature.WriteBigDecimalAsPlain,           // 序列化BigDecimal使用toPlainString，避免科学计数法
                JSONWriter.Feature.BrowserCompatible
        );
        converter.setFastJsonConfig(config);

        //
        converter.setDefaultCharset(StandardCharsets.UTF_8);

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 移除jackson
        converters.removeIf(converters::contains);
        converters.add(0,converter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
}
