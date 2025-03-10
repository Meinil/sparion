package com.meinil.system.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.system.domain.bo.SysUserBO;
import com.meinil.system.domain.vo.SysUserVO;
import com.meinil.system.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    private final ISysUserService userService;

    public SysUserController(ISysUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public R<PageResult<SysUserVO>> list(SysUserBO sysUserBO, PageQuery pageQuery) {
        return R.ok(userService.list(sysUserBO, pageQuery));
    }
}
