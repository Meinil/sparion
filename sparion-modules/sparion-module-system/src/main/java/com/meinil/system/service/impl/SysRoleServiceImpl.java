package com.meinil.system.service.impl;

import com.meinil.system.domain.entity.SysRole;
import com.meinil.system.mapper.SysRoleMapper;
import com.meinil.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper baseMapper;

    public SysRoleServiceImpl(SysRoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = baseMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            permsSet.add(perm.getRoleCode());
        }
        return permsSet;
    }
}
