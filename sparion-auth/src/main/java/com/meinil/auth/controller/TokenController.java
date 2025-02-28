package com.meinil.auth.controller;

import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.form.PasswordLoginBody;
import com.meinil.auth.form.RegisterBody;
import com.meinil.auth.service.IAuthLoginService;
import com.meinil.common.core.domain.R;
import com.meinil.common.web.utils.WebUtil;
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
     * @param loginBody 登录参数
     * @return 登录信息
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody PasswordLoginBody loginBody) {
        LoginVO login = authLoginService.login(loginBody);
        return R.ok(login);
    }

    /**
     * 登出
     */
    @DeleteMapping("/logout")
    public R<Void> logout() {
        Long userId = WebUtil.getUserId();
        return R.ok();
    }

    /**
     * 注册
     * @param registerBody
     * @return
     */
    @PostMapping("/register")
    public R<Void> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        authLoginService.register(registerBody);
        return R.ok();
    }
}
