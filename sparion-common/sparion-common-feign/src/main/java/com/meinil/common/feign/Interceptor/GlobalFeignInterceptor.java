package com.meinil.common.feign.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
@Component
public class GlobalFeignInterceptor implements RequestInterceptor {

    @Value("${security.allow-header}")
    private String allowHeader;

    @Value("${security.allow-header-value}")
    private String allowHeaderValue;

    @Override
    public void apply(RequestTemplate template) {
        template.header(allowHeader, allowHeaderValue);
    }
}
