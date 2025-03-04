package com.meinil.system.convert;

import com.meinil.system.domain.entity.SysMenu;
import com.meinil.system.domain.vo.SysMenuVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/2
 * @description
 */
@Mapper
public interface SysMenuConvert {

    SysMenuVO sysMenuToSysMenuVO(SysMenu sysMenu);

    List<SysMenuVO> sysMenuToSysMenuVO(List<SysMenu> sysMenus);
}
