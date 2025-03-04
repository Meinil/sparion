package com.meinil.auth.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.meinil.auth.domain.vo.CaptchaVO;
import com.meinil.auth.properties.AccountProperties;
import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Meinil
 * @date 2025/2/28
 * @description 验证码控制器
 */
@RestController
@RequestMapping("/auth")
public class CaptchaController {

    private final static Logger log = LoggerFactory.getLogger(CaptchaController.class);

    private final DefaultKaptcha kaptchaProducer;

    private final AccountProperties accountProperties;

    public CaptchaController(DefaultKaptcha kaptchaProducer, AccountProperties accountProperties) {
        this.kaptchaProducer = kaptchaProducer;
        this.accountProperties = accountProperties;
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public R<CaptchaVO> captcha() throws IOException {
        // 生成验证码文本
        String capText = kaptchaProducer.createText();

        // 生成验证码图片
        BufferedImage bi = kaptchaProducer.createImage(capText);

        // 将图片转换为字节数组
        CaptchaVO captchaVO = new CaptchaVO();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            // 将字节数组编码为 Base64 字符串
            String base64Image = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(bytes);

            captchaVO.setUuid(UUID.randomUUID().toString());
            captchaVO.setImg(base64Image);

            // 缓存到redis
            CacheUtil.setCacheObject(CacheConstants.CAPTCHA_CODE_KEY + captchaVO.getUuid(), capText, accountProperties.getCaptchaExpiration());
        }

        return R.ok(captchaVO);
    }
}
