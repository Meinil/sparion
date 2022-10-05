package com.meinil.sparion.bot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.sparion.bot.entity.QqAccount;
import com.meinil.sparion.bot.mapper.QqAccountMapper;
import com.meinil.sparion.bot.service.IQqAccountService;
import com.meinil.sparion.bot.vo.QqAccountCreateVo;
import com.meinil.sparion.common.result.SparionException;
import com.meinil.sparion.common.utils.CipherUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * @author Meinil
 * @date 2022/10/4
 * @description
 */
@Service
public class QqAccountServiceImpl extends ServiceImpl<QqAccountMapper, QqAccount> implements IQqAccountService {

    @Override
    public Long createQqAccount(QqAccountCreateVo createVo) {
        // 校验
        if (Objects.isNull(createVo.getQq())) {
            throw new SparionException("qq号不能为空");
        } else {
            Long count = baseMapper.selectCount(
                    Wrappers.lambdaQuery(QqAccount.class)
                            .eq(QqAccount::getQq, createVo.getQq())
            );
            if (count > 0) {
                throw new SparionException("qq账户已存在");
            }
        }

        // 秘钥
        String secretKey = null;
        if (!StringUtils.hasText(createVo.getPassword())) {
            throw new SparionException("密码不能为空");
        } else {
            // 加密密码
            secretKey = CipherUtils.generateSecret(16);
            try {
                String encrypt = CipherUtils.encrypt(secretKey, createVo.getPassword());
                createVo.setPassword(encrypt);
            } catch (GeneralSecurityException e) {
                throw new SparionException("密码加密异常");
            }
        }

        QqAccount account = new QqAccount();
        BeanUtils.copyProperties(createVo, account);

        // 密码秘钥
        account.setSecretKey(secretKey);
        // 设置账户为已启用
        account.setStatus(1);
        if (baseMapper.insert(account) > 0) {
            return account.getId();
        }

        throw new SparionException("机器人创建失败");
    }
}
