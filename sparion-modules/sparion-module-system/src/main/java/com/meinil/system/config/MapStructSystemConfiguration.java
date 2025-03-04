package com.meinil.system.config;

import com.meinil.system.convert.SysMenuConvert;
import com.meinil.system.convert.SysUserConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description mapstruct配置类
 */
@Configuration
public class MapStructSystemConfiguration {

    @Bean
    public SysUserConvert sysUserConvert() {
        return Mappers.getMapper(SysUserConvert.class);
    }

    @Bean
    public SysMenuConvert sysMenuConvert() {
        return Mappers.getMapper(SysMenuConvert.class);
    }
}
