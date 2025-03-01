package com.meinil.common.web.utils;

import com.meinil.common.web.domain.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description Web工具类
 */
public class WebUtil {

    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    private WebUtil() {}

    /**
     * 保存用户信息
     * @param userInfo 用户信息
     */
    public static void setUserInfo(UserInfo userInfo) {
        WebUtil.USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    /**
     * 删除用户信息
     */
    public static void removeUserInfo() {
        WebUtil.USER_INFO_THREAD_LOCAL.remove();
    }

    /**
     * 获取当前登录用户的信息
     * @return 用户信息
     */
    public static UserInfo getUserInfo() {
        return WebUtil.USER_INFO_THREAD_LOCAL.get();
    }

    /**
     * 获取当前登录的用户id
     * @return 用户id
     */
    public static Long getUserId() {
        return getUserInfo().getUserId();
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
