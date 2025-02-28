package com.meinil.common.web.properties;

import com.meinil.common.core.utlis.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * jwt subject
     */
    private String subject;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * token过期时间 单位: 分钟
     */
    private Long expirationTime;

    public JwtProperties() {}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }
}
