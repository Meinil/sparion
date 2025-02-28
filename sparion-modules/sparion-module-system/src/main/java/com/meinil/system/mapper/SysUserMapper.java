package com.meinil.system.mapper;

import com.meinil.system.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
public interface SysUserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户详情
     */
    SysUser selectUserByUserName(@Param("username") String username);

    /**
     * 用户插入
     * @param user 用户信息
     * @return 是否插入成功
     */
    int insertUser(SysUser user);
}
