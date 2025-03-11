package com.meinil.common.web.interceptor;

import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.domain.LoginUser;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.constants.WebConstants;
import com.meinil.common.web.exception.SparionException;
import com.meinil.common.web.utils.JwtUtil;
import com.meinil.common.web.utils.WebUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
public class TokenInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 Token
        String token = request.getHeader(WebConstants.TOKEN_HEADER);
        if (token == null || !token.startsWith("Bearer ")) {
            throw new SparionException("token不能为空");
        }

        // 去掉 "Bearer " 前缀
        token = token.substring(7);

        try {
            // 解析 Token
            Long userId = null;
            // 从Redis中获取用户信息
            if (StringUtil.equals("/auth/refresh", request.getRequestURI())) {
                userId = JwtUtil.getRefreshClaims(token, WebConstants.JWT_CLAIM_USER_ID, Long.class);
            } else {
                userId = JwtUtil.getClaims(token, WebConstants.JWT_CLAIM_USER_ID, Long.class);
                LoginUser loginUser = CacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + userId);

                // 保存用户信息到请求属性中
                WebUtil.setLoginUser(loginUser);
            }
            return true;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        WebUtil.removeLoginUser();
    }
}
