package com.meinil.common.web.utils;

import com.meinil.common.core.domain.LoginUser;
import com.meinil.common.core.utlis.CollectionUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.constants.WebConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

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
        LoginUser loginUser = WebUtil.LOGIN_USER_THREAD_LOCAL.get();
        return Objects.isNull(loginUser) ? new LoginUser() : loginUser;
    }

    /**
     * 获取当前登录的用户id
     * @return 用户id
     */
    public static Long getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取当前登录的用户明
     * @return 用户明
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取当前用户的授权令牌
     * @return 用户id
     */
    public static String getAccessToken() {
        // 从用户信息中获取token
        String accessToken = getLoginUser().getAccessToken();
        if (StringUtil.isNotBlank(accessToken)) {
            return accessToken;
        }

        // 从请求头中获取 Token
        HttpServletRequest request = getRequest();
        String token = request.getHeader(WebConstant.TOKEN_HEADER);

        // 去掉 "Bearer " 前缀
        return token.substring(7);
    }

    /**
     * 获取当前登录人的角色
     * @return 角色集合
     */
    public static Set<String> getRoleCodes() {
        return getLoginUser().getRoles();
    }

    /**
     * 获取当前登录人的权限
     * @return 权限集合
     */
    public static Set<String> getPermissions() {
        if (CollectionUtil.isNotEmpty(getLoginUser().getPermissions())) {
            return getLoginUser().getPermissions();
        }
        return Collections.emptySet();
    }

    /**
     * 判断当前登录人是否是超级管理员
     * @return
     */
    public static boolean isSuperAdmin(Long userId) {
        return Objects.nonNull(userId) && userId.equals(1L);
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
