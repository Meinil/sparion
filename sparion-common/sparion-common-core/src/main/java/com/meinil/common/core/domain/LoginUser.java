package com.meinil.common.core.domain;

import java.util.Set;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description
 */
public class LoginUser {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色权限
     */
    private Set<String> roles;

    /**
     * 菜单权限
     */
    private Set<String> permissions;

    /**
     * 授权令牌
     */
    private String accessToken;

    /**
     * 授权令牌 过期时间
     */
    private Long expireIn;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌 过期时间
     */
    private Long refreshExpireIn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

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
