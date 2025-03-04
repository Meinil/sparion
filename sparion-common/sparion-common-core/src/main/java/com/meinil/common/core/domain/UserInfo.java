package com.meinil.common.core.domain;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
public class UserInfo extends LoginUser {

    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
