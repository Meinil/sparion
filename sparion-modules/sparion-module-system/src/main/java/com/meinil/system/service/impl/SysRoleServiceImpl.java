package com.meinil.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meinil.common.core.utlis.ArrayUtil;
import com.meinil.common.core.utlis.CollectionUtil;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.common.mybatis.utils.PageUtil;
import com.meinil.common.web.exception.SparionException;
import com.meinil.system.constants.SystemConstant;
import com.meinil.system.convert.SysRoleConvert;
import com.meinil.system.domain.bo.SysRoleBO;
import com.meinil.system.domain.bo.SysRoleEditBO;
import com.meinil.system.domain.entity.SysRole;
import com.meinil.system.domain.entity.SysRoleMenu;
import com.meinil.system.domain.vo.SysRoleVO;
import com.meinil.system.mapper.SysRoleMapper;
import com.meinil.system.mapper.SysRoleMenuMapper;
import com.meinil.system.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper baseMapper;

    private final SysRoleMenuMapper roleMenuMapper;

    private final SysRoleConvert roleConvert;

    public SysRoleServiceImpl(SysRoleMapper baseMapper, SysRoleMenuMapper roleMenuMapper, SysRoleConvert roleConvert) {
        this.baseMapper = baseMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.roleConvert = roleConvert;
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = baseMapper.selectRoleByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            permsSet.add(perm.getRoleCode());
        }
        return permsSet;
    }

    @Override
    public PageResult<SysRoleVO> list(SysRoleBO sysRoleBO, PageQuery pageQuery) {
        IPage<SysRole> page = baseMapper.selectList(sysRoleBO, PageUtil.pageOf(pageQuery));
        return PageUtil.pageToPageResult(page, roleConvert::sysRoleToSysRoleVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(SysRoleBO sysRoleBO) {
        SysRole role = baseMapper.selectRoleByRoleCode(sysRoleBO.getRoleCode());
        if (Objects.nonNull(role)) {
            throw new SparionException("该角色编码已存在");
        }

        // 插入角色
        SysRole roleAdd = roleConvert.sysRoleBOToSysRole(sysRoleBO);
        roleAdd.setStatus(SystemConstant.ROLE_STATUS_1);
        baseMapper.insert(roleAdd);

        // 插入权限
        if (CollectionUtil.isEmpty(sysRoleBO.getMenuIds())) {
            return roleAdd.getId();
        }
        List<SysRoleMenu> roleMenus = sysRoleBO.getMenuIds().stream().map(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleAdd.getId());
            return roleMenu;
        }).toList();
        roleMenuMapper.insert(roleMenus);

        return roleAdd.getId();
    }

    @Override
    public Integer remove(Long[] roleIds) {
        if (ArrayUtil.isEmpty(roleIds)) {
            return 0;
        }
        List<SysRole> sysRoles = baseMapper.selectByIds(Arrays.asList(roleIds));
        for (SysRole sysRole : sysRoles) {
            if (SystemConstant.ROLE_CAN_DEL_0.equals(sysRole.getCanDel())) {
                throw new SparionException("该角色不能删除");
            }
        }

        // TODO 角色下有人的不能删除
        return baseMapper.deleteByIds(Arrays.asList(roleIds));
    }

    @Override
    public Integer edit(SysRoleEditBO roleEdit) {
        SysRole oldRole = baseMapper.selectById(roleEdit.getId());

        if (Objects.isNull(oldRole)) {
            throw new SparionException("角色id: %s不存在", roleEdit.getId());
        }

        if (SystemConstant.SUPER_ADMIN_ROLE.equals(oldRole.getRoleCode()) && SystemConstant.ROLE_STATUS_0.equals(roleEdit.getStatus())) {
            throw new SparionException("超级管理员角色禁止停用");
        }

        SysRole sysRole = roleConvert.sysRoleEditBOToSysRole(roleEdit);
        return baseMapper.updateById(sysRole);
    }
}
