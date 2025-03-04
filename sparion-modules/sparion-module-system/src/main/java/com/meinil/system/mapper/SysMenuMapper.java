package com.meinil.system.mapper;

import com.meinil.system.domain.entity.SysMenu;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface SysMenuMapper {

    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 查询所有菜单
     * @return
     */
    List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户id查询用户的菜单
     * @param userId 用户id
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
