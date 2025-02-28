package com.meinil.auth.config;

import com.meinil.auth.convert.AuthLoginConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@Configuration
public class MapStructAuthConfiguration {

    @Bean
    public AuthLoginConvert authLoginConvert() {
        return Mappers.getMapper(AuthLoginConvert.class);
    }
}
