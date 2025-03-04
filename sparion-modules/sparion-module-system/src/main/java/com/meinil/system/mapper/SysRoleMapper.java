package com.meinil.system.mapper;

import com.meinil.system.domain.entity.SysRole;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface SysRoleMapper {
    /**
     * 根据用户id查询用户角色
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);
}
