package com.meinil.system.api.service;

import com.meinil.common.core.domain.R;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description
 */
public interface IUserFeignApi {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/feign/system/user")
    R<LoginUser> getUserInfo(@RequestParam("username") String username);

    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 是否成功
     */
    @PostMapping("/feign/system/register")
    R<Boolean> registerUserInfo(@RequestBody RegisterDTO registerDTO);
}
