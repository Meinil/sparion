package com.meinil.sparion.common.utils;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Meinil
 * @date 2022/10/2
 * @description
 */
@Data
public class JwtUtils {

    @ApiModelProperty("token过期时间(s)")
    private Long expire;

    @ApiModelProperty("token加密秘钥")
    private String secret;

    public String getJwtToken(String id){
        // 开始时间
        LocalDateTime startDate = LocalDateTime.now();
        // 过期时间
        LocalDateTime expireDate = startDate.plusMinutes(expire);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("Meinil")
                .setIssuedAt(Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireDate.atZone(ZoneId.systemDefault()).toInstant()))
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     */
    public boolean checkToken(String token) {
        if(Strings.isNullOrEmpty(token)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     */
    public boolean checkToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if(Strings.isNullOrEmpty(token)) {
                return false;
            }
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取用户id
     */
    public String getUserIdByRequest(HttpServletRequest request) {
        return getUserIdByToken(request.getHeader("Authorization"));
    }

    public String getUserIdByToken(String token) {
        if(Strings.isNullOrEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
