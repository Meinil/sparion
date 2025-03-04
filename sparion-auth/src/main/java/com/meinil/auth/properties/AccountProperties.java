package com.meinil.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Meinil
 * @date 2025/3/4
 * @description
 */
@Component
@ConfigurationProperties(prefix = "account")
public class AccountProperties {

    /**
     * 密码最大重试次数 默认: 5次
     */
    private Integer maxRetryCount = 5;

    /**
     * 是否启用验证码登录 0-不启用 1-启用 默认: 启用
     */
    private String enableCaptcha = "1";

    /**
     * 验证码有效期 默认: 2分钟
     */
    private Long captchaExpiration = 2L;

    /**
     * 密码超过最大登录次数后锁定时间 默认: 10分钟
     */
    private Long lockTime = 10L;

    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public String getEnableCaptcha() {
        return enableCaptcha;
    }

    public void setEnableCaptcha(String enableCaptcha) {
        this.enableCaptcha = enableCaptcha;
    }

    public Long getCaptchaExpiration() {
        return captchaExpiration;
    }

    public void setCaptchaExpiration(Long captchaExpiration) {
        this.captchaExpiration = captchaExpiration;
    }

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }
}
