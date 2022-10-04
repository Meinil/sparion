package com.meinil.sparion.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.sparion.common.entity.Permission;

import java.util.List;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 权限mapper接口
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> queryPermsByUserId(Long userId);
}
