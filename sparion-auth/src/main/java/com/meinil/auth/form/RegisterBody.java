package com.meinil.auth.form;

import com.meinil.common.core.domain.LoginBody;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description 注册实体
 */
public class RegisterBody extends LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
