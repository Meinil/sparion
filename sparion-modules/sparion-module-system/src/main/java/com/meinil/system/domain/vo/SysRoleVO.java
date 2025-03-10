package com.meinil.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Meinil
 * @date 2025/3/9
 * @description
 */
public class SysRoleVO {

    /**
     * 角色id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

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

    /**
     * 能否删除 1-可以删除 0-不能删除
     */
    private String canDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCanDel() {
        return canDel;
    }

    public void setCanDel(String canDel) {
        this.canDel = canDel;
    }
}
