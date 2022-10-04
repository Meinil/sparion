package com.meinil.sparion.common.filters;

import com.meinil.sparion.common.entity.LoginUser;
import com.meinil.sparion.common.utils.JwtUtils;
import com.meinil.sparion.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 获取token
        String token = request.getHeader("Authorization");
            // 未登录放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. 解析token
        String userId = "";
        try {
            userId = jwtUtils.getUserIdByToken(token);
        } catch(Exception e) {
            throw new BadCredentialsException("token非法");
        }

        // 3. 获取userId,并获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("login:" + userId);
        if (loginUser == null) {
            throw new AccountExpiredException("用户身份信息已过期,请重新登录");
        }

        // 4. 封装Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginUser,                              // 用户信息
                null,
                loginUser.getAuthorities()              // 权限信息
        );

        // 5. 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 放行执行后续的过滤器
        filterChain.doFilter(request, response);
    }
}
