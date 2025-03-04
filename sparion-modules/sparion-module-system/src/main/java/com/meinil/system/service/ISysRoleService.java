package com.meinil.system.service;

import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface ISysRoleService {

    /**
     * 根据用户id查询用户所拥有的角色
     * @param userId 用户id
     * @return 角色编码
     */
    Set<String> selectRolePermissionByUserId(Long userId);

}
