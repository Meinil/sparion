package com.meinil.system.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/9
 * @description
 */
public class SysRoleBO {

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色序号
     */
    private Integer roleSort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 能否删除 1-可以删除 0-不能删除
     */
    private String canDel;

    /**
     * 菜单权限
     */
    private List<Long> menuIds;

    public @NotBlank String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(@NotBlank String roleCode) {
        this.roleCode = roleCode;
    }

    public @NotBlank String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCanDel() {
        return canDel;
    }

    public void setCanDel(String canDel) {
        this.canDel = canDel;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
