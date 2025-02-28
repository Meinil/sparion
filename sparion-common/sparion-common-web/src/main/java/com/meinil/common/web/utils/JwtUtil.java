package com.meinil.common.web.utils;

import com.meinil.common.web.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
public class JwtUtil {

    private JwtUtil() {}

    /**
     * 创建token
     * @param claims claims
     * @param subject 主体
     * @param expirationTime token有效时长, 单位: 分钟
     * @param secretKey 密钥
     * @return token
     */
    public static String createToken(Map<String, Object> claims, String subject, Long expirationTime, String secretKey) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .claims(claims)
                .expiration(new Date(currentTimeMillis + expirationTime * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    /**
     * 创建token
     * @param claims claims
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {
        JwtProperties jwtProperties = SpringUtil.getBean(JwtProperties.class);
        return createToken(claims, jwtProperties.getSubject(), jwtProperties.getExpirationTime(), jwtProperties.getSecretKey());
    }

    /**
     * 获取claims
     * @param token token
     * @param key 获取claim的key
     * @param clazz claim的类型
     * @return claim
     */
    public static <T> T getClaims(String token, String key, Class<T> clazz) {
        JwtProperties jwtProperties = SpringUtil.getBean(JwtProperties.class);

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(key, clazz);
    }
}
