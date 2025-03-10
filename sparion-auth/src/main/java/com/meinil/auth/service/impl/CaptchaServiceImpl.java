package com.meinil.auth.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.meinil.auth.domain.vo.CaptchaVO;
import com.meinil.auth.properties.AccountProperties;
import com.meinil.auth.service.ICaptchaService;
import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.exception.SparionException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Meinil
 * @date 2025/3/8
 * @description
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    private final DefaultKaptcha kaptchaProducer;

    private final AccountProperties accountProperties;

    public CaptchaServiceImpl(DefaultKaptcha kaptchaProducer, AccountProperties accountProperties) {
        this.kaptchaProducer = kaptchaProducer;
        this.accountProperties = accountProperties;
    }

    @Override
    public CaptchaVO captcha(String uuid) {
        if (StringUtil.isNoneBlank(uuid)) {
            // 清除上次生成的验证码
            CacheUtil.deleteObject(CacheConstants.CAPTCHA_CODE_KEY + uuid);
        }

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
        } catch (Exception e) {
            throw new SparionException(e);
        }

        return captchaVO;
    }
}
