package com.meinil.auth.controller;

import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.domain.vo.TokenVO;
import com.meinil.auth.form.PasswordLoginBody;
import com.meinil.auth.form.RegisterBody;
import com.meinil.auth.service.IAuthLoginService;
import com.meinil.common.core.domain.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
@RestController
@RequestMapping("/auth")
public class TokenController {

    private final IAuthLoginService authLoginService;

    public TokenController(IAuthLoginService authLoginService) {
        this.authLoginService = authLoginService;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody @Validated PasswordLoginBody loginBody) {
        LoginVO login = authLoginService.login(loginBody);
        return R.data(login);
    }

    /**
     * 登出
     */
    @DeleteMapping("/logout")
    public R<Void> logout() {
        authLoginService.logout();
        return R.ok();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public R<Void> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        authLoginService.register(registerBody);
        return R.ok();
    }

    /**
     * token刷新
     */
    @PostMapping("/refresh")
    public R<TokenVO> refresh() {
        return R.data(authLoginService.refresh());
    }
}
