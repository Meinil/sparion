package com.meinil.system.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.system.domain.bo.SysRoleBO;
import com.meinil.system.domain.bo.SysRoleEditBO;
import com.meinil.system.domain.vo.SysRoleVO;
import com.meinil.system.service.ISysRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2025/3/9
 * @description
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    private final ISysRoleService roleService;

    public SysRoleController(ISysRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 角色分页查询
     */
    @GetMapping("/list")
    public R<PageResult<SysRoleVO>> list(SysRoleBO sysRoleBO, PageQuery pageQuery) {
        return R.ok(roleService.list(sysRoleBO, pageQuery));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public R<String> add(@RequestBody @Validated SysRoleBO sysRoleBO) {
        Long roleId = roleService.add(sysRoleBO);
        return R.ok(String.valueOf(roleId));
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    public R<Integer> edit(@Validated @RequestBody SysRoleEditBO roleEdit) {;
        return R.ok(roleService.edit(roleEdit));
    }

    /**
     * 根据角色id删除角色
     */
    @DeleteMapping("/{roleIds}")
    public R<Integer> remove(@PathVariable(name = "roleIds") Long[] roleIds) {
        return R.ok(roleService.remove(roleIds));
    }
}
