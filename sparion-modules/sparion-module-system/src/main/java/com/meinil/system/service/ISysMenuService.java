package com.meinil.system.service;

import com.meinil.system.domain.vo.SysMenuVO;

import java.util.List;
import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
public interface ISysMenuService {
    /**
     * 根据用户id查询用户权限
     * @param userId 用户id
     * @return
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户id查询用户菜单
     * @param userId 用户id
     * @return
     */
    List<SysMenuVO> selectMenuTreeByUserId(Long userId);
}
