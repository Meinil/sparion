package com.meinil.sparion.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.sparion.bot.entity.BotInfo;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
public interface IBotInfoService extends IService<BotInfo> {

    /**
     * 根据qqAccountId创建一个机器人
     * @param id qqAccountId
     * @return 是否创建成功
     */
    Boolean createBot(Long id);
}
