package com.meinil.system.service;

import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.common.core.domain.UserInfo;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
public interface ISysUserService {

    UserInfo getLoginUser(String username);

    Boolean register(RegisterDTO registerDTO);
}
