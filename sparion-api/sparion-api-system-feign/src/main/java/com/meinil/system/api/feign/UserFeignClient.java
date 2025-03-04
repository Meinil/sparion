package com.meinil.system.api.feign;

import com.meinil.common.core.domain.R;
import com.meinil.common.core.domain.UserInfo;
import com.meinil.system.api.dto.RegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Meinil
 * @date 2025/2/23
 * @description
 */
@FeignClient(name = "${sparion.applications.sparion-module-system}")
public interface UserFeignClient {
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/feign/system/user")
    R<UserInfo> getUserInfo(@RequestParam("username") String username);

    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 是否成功
     */
    @PostMapping("/feign/system/register")
    R<Boolean> registerUserInfo(@RequestBody RegisterDTO registerDTO);
}
