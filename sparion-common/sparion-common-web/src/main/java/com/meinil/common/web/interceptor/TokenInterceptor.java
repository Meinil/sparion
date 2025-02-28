package com.meinil.common.web.interceptor;

import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.web.constants.WebConstants;
import com.meinil.common.web.domain.UserInfo;
import com.meinil.common.web.utils.JwtUtil;
import com.meinil.common.web.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 Token
        String token = request.getHeader(WebConstants.TOKEN_HEADER);
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("token不能为空");
        }

        // 去掉 "Bearer " 前缀
        token = token.substring(7);

        try {
            // 解析 Token
            Long userId = JwtUtil.getClaims(token, WebConstants.JWT_CLAIM_USER_ID, Long.class);

            // 从Redis中获取用户信息
            UserInfo userInfo = CacheUtil.getCacheObject(CacheConstants.CACHE_LOGIN_USER_PREFIX + userId + ":" + token);

            // 保存用户信息到请求属性中
            WebUtil.setUserInfo(userInfo);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        WebUtil.removeUserInfo();
    }
}
