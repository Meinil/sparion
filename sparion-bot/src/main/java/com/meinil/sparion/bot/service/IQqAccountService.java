package com.meinil.sparion.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.sparion.bot.entity.QqAccount;
import com.meinil.sparion.bot.vo.QqAccountCreateVo;

/**
 * @author Meinil
 * @date 2022/10/4
 * @description
 */
public interface IQqAccountService extends IService<QqAccount> {
    /**
     * 创建一个qq账户
     * @param createVo qq信息
     * @return 账户id
     */
    Long createQqAccount(QqAccountCreateVo createVo);
}
