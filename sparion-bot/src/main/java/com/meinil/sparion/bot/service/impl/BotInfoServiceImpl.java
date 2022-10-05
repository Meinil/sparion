package com.meinil.sparion.bot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.sparion.bot.entity.BotInfo;
import com.meinil.sparion.bot.entity.QqAccount;
import com.meinil.sparion.bot.mapper.BotInfoMapper;
import com.meinil.sparion.bot.service.IBotInfoService;
import com.meinil.sparion.bot.service.IQqAccountService;
import com.meinil.sparion.common.result.SparionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@Service
public class BotInfoServiceImpl extends ServiceImpl<BotInfoMapper, BotInfo> implements IBotInfoService {

    @Autowired
    private IQqAccountService qqAccountService;

    @Override
    public Boolean createBot(Long id) {
        // 查询qq账户
        QqAccount account = qqAccountService.lambdaQuery()
                .eq(QqAccount::getId, id)
                .one();
        if (Objects.isNull(account)) {
            throw new SparionException("qq不存在");
        }
        return null;
    }
}
