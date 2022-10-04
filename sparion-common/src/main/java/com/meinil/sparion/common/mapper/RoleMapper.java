package com.meinil.sparion.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.sparion.common.entity.Role;

import java.util.List;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 角色Mapper类
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> queryRolesByUserId(Long userId);
}
