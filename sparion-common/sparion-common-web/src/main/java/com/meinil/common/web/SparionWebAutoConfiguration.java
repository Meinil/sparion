package com.meinil.common.web;

import com.meinil.common.web.config.WebConfig;
import com.meinil.common.web.handler.GlobalExceptionHandler;
import com.meinil.common.web.properties.SecurityProperties;
import com.meinil.common.web.utils.SpringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.meinil.common.web.properties.JwtProperties;
import org.springframework.context.annotation.Import;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class, SecurityProperties.class})
@Import({SpringUtil.class, WebConfig.class, GlobalExceptionHandler.class})
public class SparionWebAutoConfiguration {

}
