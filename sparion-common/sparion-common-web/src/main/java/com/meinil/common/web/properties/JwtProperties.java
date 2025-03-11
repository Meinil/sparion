package com.meinil.common.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
     * accessToken 密钥
     */
    private String secretKey;

    /**
     * accessToken 过期时间 单位: 分钟
     */
    private Long expirationTime;

    /**
     * accessToken 密钥
     */
    private String refreshSecretKey;

    /**
     * refreshToken 过期时间 单位: 分钟
     */
    private Long refreshExpirationTime;

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

    public String getRefreshSecretKey() {
        return refreshSecretKey;
    }

    public void setRefreshSecretKey(String refreshSecretKey) {
        this.refreshSecretKey = refreshSecretKey;
    }

    public Long getRefreshExpirationTime() {
        return refreshExpirationTime;
    }

    public void setRefreshExpirationTime(Long refreshExpirationTime) {
        this.refreshExpirationTime = refreshExpirationTime;
    }
}
