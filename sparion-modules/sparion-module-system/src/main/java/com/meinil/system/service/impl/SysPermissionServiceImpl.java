package com.meinil.system.service.impl;

import com.meinil.common.web.utils.WebUtil;
import com.meinil.system.service.ISysMenuService;
import com.meinil.system.service.ISysPermissionService;
import com.meinil.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

    private final ISysRoleService roleService;

    private final ISysMenuService menuService;

    public SysPermissionServiceImpl(ISysRoleService roleService, ISysMenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }

    /**
     * 获取角色数据权限
     *
     * @param userId  用户id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (WebUtil.isSuperAdmin(userId)) {
            roles.add("SUPER_ADMIN");
        } else {
            Set<String> temp = roleService.selectRolePermissionByUserId(userId);
            roles.addAll(temp);
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId  用户id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (WebUtil.isSuperAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
