package com.meinil.auth.service;

import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.form.PasswordLoginBody;
import com.meinil.auth.form.RegisterBody;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
public interface IAuthLoginService {

    /**
     * 登录
     * @param loginBody 登录实体
     * @return 授权认证信息
     */
    LoginVO login(PasswordLoginBody loginBody);

    /**
     * 注册
     * @param registerBody 注册实体
     */
    void register(RegisterBody registerBody);

    /**
     * 登出
     */
    void logout();
}
