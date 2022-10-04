package com.meinil.sparion.common.filters;

import com.meinil.sparion.common.entity.LoginUser;
import com.meinil.sparion.common.entity.Permission;
import com.meinil.sparion.common.mapper.PermissionMapper;
import com.meinil.sparion.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Meinil
 * @date 2022/9/26
 * @description 过滤需要认证的url
 */
@Component
public class DynamicallyUrlFilter extends OncePerRequestFilter {
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 未登录的情况
        if ("anonymousUser".equals(principal)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取用户信息
        LoginUser loginUser = (LoginUser)principal;

        // 获取系统权限列表
        List<Permission> perms = redisCache.getCacheList("perms");
        if (perms == null || perms.size() == 0) {
            perms = permissionMapper.selectList(null);
            redisCache.setCacheList("perms", perms);
        }

        Set<Permission> paths = perms.stream()
                .filter(perm -> request.getRequestURI().equals(perm.getPath()))
                .collect(Collectors.toSet());
        // 此路径没有权限不需要校验
        if (paths.size() == 0) {
            filterChain.doFilter(request, response);
            return;
        }

        for (Permission path : paths) {
            // 校验通过
            if (loginUser.getPermissions().contains(path.getPerms())) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        throw new AccessDeniedException("权限不足");
    }
}
