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


    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌 refresh_token 的有效期
     */
    private Long refreshExpireIn;

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getRefreshExpireIn() {
        return refreshExpireIn;
    }

    public void setRefreshExpireIn(Long refreshExpireIn) {
        this.refreshExpireIn = refreshExpireIn;
    }
}
