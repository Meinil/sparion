package com.meinil.sparion.common.controller;

import com.meinil.sparion.common.model.UserLoginModel;
import com.meinil.sparion.common.service.IUserService;
import com.meinil.sparion.common.vo.UserLoginVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description 用户管理
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public UserLoginModel login(@RequestBody UserLoginVO userLoginVO) {
        return userService.login(userLoginVO);
    }

    @PostMapping("/logout")
    @ApiOperation("登出")
    public Boolean logout() {
        return userService.logout();
    }
}
