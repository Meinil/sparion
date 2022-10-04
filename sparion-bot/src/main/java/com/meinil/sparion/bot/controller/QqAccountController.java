package com.meinil.sparion.bot.controller;

import com.meinil.sparion.bot.service.IQqAccountService;
import com.meinil.sparion.bot.vo.QqAccountCreateVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2022/10/4
 * @description
 */
@RestController
@RequestMapping("/bot")
public class QqAccountController {

    @Autowired
    private IQqAccountService qqAccountService;

    @PostMapping("/create/account")
    @ApiOperation("创建一个qq账户")
    public Long create(@RequestBody QqAccountCreateVo createVo) {
        return qqAccountService.createQqAccount(createVo);
    }
}
