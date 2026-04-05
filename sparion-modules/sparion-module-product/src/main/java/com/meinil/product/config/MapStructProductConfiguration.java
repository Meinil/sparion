package com.meinil.product.config;

import com.meinil.product.convert.ProdSoftConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meinil
 * @date 2026/3/31
 * @description mapstruct配置类
 */
@Configuration
public class MapStructProductConfiguration {

    @Bean
    public ProdSoftConvert prodSoftConvert() {
        return Mappers.getMapper(ProdSoftConvert.class);
    }
}
