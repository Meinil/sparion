package com.meinil.system.domain.entity;

import com.meinil.common.core.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description 用户角色关联实体
 */
public class SysUserRole extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
