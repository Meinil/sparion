package com.meinil.system.domain.entity;

import com.meinil.common.core.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description 角色实体
 */
public class SysRole extends BaseEntity {

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 角色排序
     */
    private Integer roleSort;

    /**
     * 备注
     */
    private String remark;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
