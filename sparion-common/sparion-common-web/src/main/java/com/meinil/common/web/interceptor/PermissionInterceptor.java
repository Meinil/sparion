package com.meinil.common.web.interceptor;

import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.exception.SparionException;
import com.meinil.common.web.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/18
 * @description 接口权限校验
 *
 * 接口的权限为 system:user:list
 * 用户拥有以下四种权限其一均可访问
 * 1. system:user:list
 * 2. system:user:*
 * 3. system:*:*
 * 4. *:*:*
 *
 */
public class PermissionInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 当前接口的权限
        String permission = CacheUtil.getCacheMapValue(CacheConstants.INTERFACE_PERMISSION, request.getRequestURI());
        if (StringUtil.isBlank(permission)) {
            return true;
        }

        // 当前登录人的权限
        Set<String> permissions = WebUtil.getPermissions();

        String[] permissionSplit = permission.split(":");
        for (int i = permissionSplit.length - 1; i > 0; i--) {
            if ("*".equals(permissionSplit[i])) {
                continue;
            }

            permissionSplit[i] = "*";
            String join = String.join(":", permissionSplit);
            if (permissions.contains(join)) {
                return true;
            }
        }

        throw new SparionException("无权访问此api");
    }
}
