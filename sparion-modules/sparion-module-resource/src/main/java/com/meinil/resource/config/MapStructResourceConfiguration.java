package com.meinil.resource.config;

import com.meinil.resource.convert.ResFileConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meinil
 * @date 2026/4/5
 * @description mapstruct配置类
 */
@Configuration
public class MapStructResourceConfiguration {

    @Bean
    public ResFileConvert resFileConvert() {
        return Mappers.getMapper(ResFileConvert.class);
    }
}
