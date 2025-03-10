package com.meinil.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meinil.system.domain.bo.SysRoleBO;
import com.meinil.system.domain.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户id查询用户角色
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 根据角色编码查询角色
     * @param roleCode 角色编码
     * @return
     */
    SysRole selectRoleByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 角色分页查询
     * @param query 查询条件
     * @param page 分页条件
     * @return 分页结果
     */
    IPage<SysRole> selectList(@Param("query") SysRoleBO query, IPage<SysRole> page);
}
