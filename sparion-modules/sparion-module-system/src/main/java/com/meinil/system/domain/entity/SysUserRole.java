package com.meinil.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.common.mybatis.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description 用户角色关联实体
 */
@TableName("sys_user_role")
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
