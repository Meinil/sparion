package com.meinil.system.convert;

import com.meinil.system.domain.bo.SysRoleBO;
import com.meinil.system.domain.bo.SysRoleEditBO;
import com.meinil.system.domain.entity.SysRole;
import com.meinil.system.domain.vo.SysRoleVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/9
 * @description
 */
@Mapper
public interface SysRoleConvert {

    List<SysRoleVO> sysRoleToSysRoleVO(List<SysRole> sysRoles);

    SysRole sysRoleBOToSysRole(SysRoleBO sysRoleBO);

    SysRole sysRoleEditBOToSysRole(SysRoleEditBO sysRoleEditBO);
}
