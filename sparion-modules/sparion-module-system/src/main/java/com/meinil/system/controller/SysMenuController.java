package com.meinil.system.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.web.utils.WebUtil;
import com.meinil.system.domain.vo.SysMenuVO;
import com.meinil.system.service.ISysMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/2
 * @description
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    private final ISysMenuService menuService;

    public SysMenuController(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取用户的路由信息
     * @return
     */
    @GetMapping("/routers")
    public R<List<SysMenuVO>> routers() {
        Long userId = WebUtil.getUserId();
        List<SysMenuVO> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menus);
    }
}
