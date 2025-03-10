package com.meinil.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.common.mybatis.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description 角色-菜单关联实体
 */
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
