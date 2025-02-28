package com.meinil.system.service;

import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
public interface ISysUserService {

    LoginUser getLoginUser(String username);

    Boolean register(RegisterDTO registerDTO);
}
