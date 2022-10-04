package com.meinil.sparion.common.configuration;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "authorization")
public class JwtProperties {
    @ApiModelProperty("token过期时间(s)")
    private Long expire;

    @ApiModelProperty("token加密秘钥")
    private String secret;
}
