package com.meinil.system.service;

import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.system.domain.bo.SysRoleBO;
import com.meinil.system.domain.bo.SysRoleEditBO;
import com.meinil.system.domain.vo.SysRoleVO;

import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface ISysRoleService {

    /**
     * 根据用户id查询用户所拥有的角色
     * @param userId 用户id
     * @return 角色编码
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 角色分页查询
     * @param sysRoleBO 查询条件
     * @param pageQuery 分页条件
     * @return 分页
     */
    PageResult<SysRoleVO> list(SysRoleBO sysRoleBO, PageQuery pageQuery);

    /**
     * 新增角色
     * @param sysRoleBO 新增参数
     * @return 角色id
     */
    Long add(SysRoleBO sysRoleBO);

    /**
     * 根据角色id删除角色
     * @param roleIds 角色id
     * @return 删除成功条数
     */
    Integer remove(Long[] roleIds);

    /**
     * 修改角色
     * @param roleEdit 更新的属性
     * @return 更新成功的条数
     */
    Integer edit(SysRoleEditBO roleEdit);
}
