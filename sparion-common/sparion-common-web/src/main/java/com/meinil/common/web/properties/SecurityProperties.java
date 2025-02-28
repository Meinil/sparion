package com.meinil.common.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 允许的请求头值 从gateway转发至各微服务需要校验该请求头
     */
    private String allowHeader;

    /**
     * 允许的请求头值
     */
    private String allowHeaderValue;

    public SecurityProperties() {}

    public String getAllowHeader() {
        return allowHeader;
    }

    public void setAllowHeader(String allowHeader) {
        this.allowHeader = allowHeader;
    }

    public String getAllowHeaderValue() {
        return allowHeaderValue;
    }

    public void setAllowHeaderValue(String allowHeaderValue) {
        this.allowHeaderValue = allowHeaderValue;
    }
}
