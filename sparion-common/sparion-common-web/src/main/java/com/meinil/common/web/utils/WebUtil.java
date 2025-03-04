package com.meinil.common.web.utils;

import com.meinil.common.core.domain.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description Web工具类
 */
public class WebUtil {

    private static final ThreadLocal<LoginUser> LOGIN_USER_THREAD_LOCAL = new ThreadLocal<>();

    private WebUtil() {}

    /**
     * 保存用户信息
     * @param loginUser 用户信息
     */
    public static void setLoginUser(LoginUser loginUser) {
        WebUtil.LOGIN_USER_THREAD_LOCAL.set(loginUser);
    }

    /**
     * 删除用户信息
     */
    public static void removeLoginUser() {
        WebUtil.LOGIN_USER_THREAD_LOCAL.remove();
    }

    /**
     * 获取当前登录用户的信息
     * @return 用户信息
     */
    public static LoginUser getLoginUser() {
        return WebUtil.LOGIN_USER_THREAD_LOCAL.get();
    }

    /**
     * 获取当前登录的用户id
     * @return 用户id
     */
    public static Long getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取当前用户的授权令牌
     * @return 用户id
     */
    public static String getAccessToken() {
        return getLoginUser().getAccessToken();
    }

    /**
     * 判断当前登录人是否是超级管理员
     * @return
     */
    public static boolean isSuperAdmin(Long userId) {
        return userId.equals(1L);
    }

    /**
     * 判断当前登录人是否是超级管理员
     * @return
     */
    public static boolean isSuperAdmin() {
        return isSuperAdmin(getUserId());
    }

    /**
     * 获取请求的上下文对象
     * @return 请求上下文对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
