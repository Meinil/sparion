package com.meinil.system.api;

import com.meinil.common.core.domain.R;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import com.meinil.system.api.service.IUserFeignApi;
import com.meinil.system.service.ISysUserService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description Feign controller
 */
@RestController
public class UserFeignClientImpl implements IUserFeignApi {

    private final ISysUserService userService;

    public UserFeignClientImpl(ISysUserService userService) {
        this.userService = userService;
    }

    @Override
    public R<LoginUser> getUserInfo(String username) {
        return R.ok(userService.getLoginUser(username));
    }

    @Override
    public R<Boolean> registerUserInfo(RegisterDTO registerDTO) {
        return R.ok(userService.register(registerDTO));
    }
}
