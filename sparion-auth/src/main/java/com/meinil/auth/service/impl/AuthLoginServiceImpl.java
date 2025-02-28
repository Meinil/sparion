package com.meinil.auth.service.impl;

import com.meinil.auth.convert.AuthLoginConvert;
import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.form.PasswordLoginBody;
import com.meinil.auth.form.RegisterBody;
import com.meinil.auth.service.IAuthLoginService;
import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.domain.R;
import com.meinil.common.web.properties.JwtProperties;
import com.meinil.common.web.utils.JwtUtil;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import com.meinil.system.api.feign.UserFeignClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 登录实现
 */
@Service
public class AuthLoginServiceImpl implements IAuthLoginService {

    private final UserFeignClient userFeignClient;

    private final JwtProperties jwtProperties;

    private final AuthLoginConvert authLoginConvert;

    public AuthLoginServiceImpl(UserFeignClient userFeignClient, JwtProperties jwtProperties, AuthLoginConvert authLoginConvert) {
        this.userFeignClient = userFeignClient;
        this.jwtProperties = jwtProperties;
        this.authLoginConvert = authLoginConvert;
    }

    @Override
    public LoginVO login(PasswordLoginBody loginBody) {
        R<LoginUser> result = userFeignClient.getUserInfo(loginBody.getUsername());
        if (result.isFail()) {
            throw new RuntimeException(result.getMsg());
        }

        LoginUser userInfo = result.getData();
        if (Objects.isNull(userInfo)) {
            throw new RuntimeException("用户不存在, 请先注册");
        }

        // 1. 校验密码
        if (!loginBody.getPassword().equals(userInfo.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 2. 创建token
        Map<String, Object> map = new HashMap<>() {{
            put("userId", userInfo.getUserId());
            put("username", userInfo.getUsername());
        }};
        String token = JwtUtil.createToken(map);
        LoginVO loginVO = new LoginVO();
        loginVO.setAccessToken(token);

        // 3. 用户信息缓存到redis
        String key = String.format("%s%s:%s", CacheConstants.CACHE_LOGIN_USER_PREFIX, userInfo.getUserId(), token);
        CacheUtil.setCacheObject(key, userInfo, jwtProperties.getExpirationTime(), TimeUnit.MINUTES);
        return loginVO;
    }

    @Override
    public void register(RegisterBody registerBody) {
        RegisterDTO registerDTO = authLoginConvert.registerBodyToRegisterDTO(registerBody);
        R<Boolean> result = userFeignClient.registerUserInfo(registerDTO);
        if (result.isFail()) {
            throw new RuntimeException("注册失败: " + result.getMsg());
        }
    }
}
