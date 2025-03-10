package com.meinil.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/10
 * @description
 */
public class SysRoleEditBO {

    /**
     * 角色id
     */
    @JsonFormat
    @NotNull(message = "角色id不能为空")
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色序号
     */
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单权限
     */
    private List<Long> menuIds;

    public @NotNull(message = "角色id不能为空") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "角色id不能为空") Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
