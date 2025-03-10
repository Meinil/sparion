package com.meinil.auth.controller;

import com.meinil.auth.domain.vo.CaptchaVO;
import com.meinil.auth.service.ICaptchaService;
import com.meinil.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2025/2/28
 * @description 验证码控制器
 */
@RestController
@RequestMapping("/auth")
public class CaptchaController {

    private final static Logger log = LoggerFactory.getLogger(CaptchaController.class);

    private final ICaptchaService captchaService;

    public CaptchaController(ICaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public R<CaptchaVO> captcha(@RequestParam(required = false, name = "uuid") String uuid) {
        return R.ok(captchaService.captcha(uuid));
    }
}
