package com.meinil.auth.form;

import com.meinil.common.core.domain.LoginBody;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 用户名密码登录
 */
public class PasswordLoginBody extends LoginBody {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
