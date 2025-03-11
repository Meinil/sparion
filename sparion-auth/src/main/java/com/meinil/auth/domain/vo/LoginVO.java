package com.meinil.auth.domain.vo;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 登录返回信息
 */
public class LoginVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色编码
     */
    private List<String> roles;

    /**
     * 权限
     */
    private List<String> permissions;

    /**
     * 授权令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 授权令牌 access_token 的有效期
     */
    private Long expireIn;

    /**
     * 刷新令牌 refresh_token 的有效期
     */
    private Long refreshExpireIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    public Long getRefreshExpireIn() {
        return refreshExpireIn;
    }

    public void setRefreshExpireIn(Long refreshExpireIn) {
        this.refreshExpireIn = refreshExpireIn;
    }
}
