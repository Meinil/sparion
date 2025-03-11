package com.meinil.auth.domain.vo;

/**
 * @author Meinil
 * @date 2025/3/10
 * @description
 */
public class TokenVO {

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private Long expireIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }
}
