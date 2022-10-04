package com.meinil.sparion.common.configuration;

import com.meinil.sparion.common.utils.JwtUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
@Configuration
public class JwtConfiguration {
    @Bean
    public JwtUtils jwtUtils(JwtProperties jwtProperties) {
        JwtUtils bean = new JwtUtils();

        if (jwtProperties.getExpire() != null) {
            bean.setExpire(jwtProperties.getExpire());
        } else {
            bean.setExpire(60 * 60 * 24L);
        }

        if (StringUtils.hasText(jwtProperties.getSecret())) {
            bean.setSecret(jwtProperties.getSecret());
        } else {
            bean.setSecret(generateSecret(128));
        }
        return bean;
    }

    /**
     * 生成秘钥
     * @param length 秘钥的长度
     * @return 秘钥
     */
    private static String generateSecret(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append((char)(random.nextInt(94) + 33));
        }
        return sb.toString();
    }
}
