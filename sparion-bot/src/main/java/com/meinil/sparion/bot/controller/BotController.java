package com.meinil.sparion.bot.controller;

import com.meinil.sparion.bot.service.IBotInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    private IBotInfoService botInfoService;

    @PostMapping("/create/{id}")
    @ApiOperation("创建一个机器人")
    public Boolean create(@PathVariable("id") @ApiParam("qq账户id") Long id) {
        return botInfoService.createBot(id);
    }
}
