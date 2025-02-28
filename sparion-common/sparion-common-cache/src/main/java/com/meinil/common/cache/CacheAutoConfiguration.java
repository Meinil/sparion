package com.meinil.common.cache;

import com.meinil.common.cache.config.RedisConfig;
import com.meinil.common.cache.utils.CacheUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Meinil
 * @date 2025/2/25
 * @description
 */
@Configuration
@Import({RedisConfig.class, CacheUtil.class})
public class CacheAutoConfiguration {

}
