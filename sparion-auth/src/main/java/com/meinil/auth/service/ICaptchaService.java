package com.meinil.auth.service;

import com.meinil.auth.domain.vo.CaptchaVO;

/**
 * @author Meinil
 * @date 2025/3/8
 * @description
 */
public interface ICaptchaService {

    /**
     * 生成验证码
     * @param uuid 上一张验证码的uuid
     * @return 验证码信息
     */
    CaptchaVO captcha(String uuid);
}
