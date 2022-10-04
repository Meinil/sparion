package com.meinil.sparion.common.configuration;

import com.meinil.sparion.common.utils.CipherUtils;
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
            bean.setSecret(CipherUtils.generateSecret(128));
        }
        return bean;
    }
}
