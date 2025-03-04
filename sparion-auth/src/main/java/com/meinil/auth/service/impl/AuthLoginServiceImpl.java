package com.meinil.auth.service.impl;

import com.meinil.auth.constants.AuthConstants;
import com.meinil.auth.convert.AuthLoginConvert;
import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.form.PasswordLoginBody;
import com.meinil.auth.form.RegisterBody;
import com.meinil.auth.properties.AccountProperties;
import com.meinil.auth.service.IAuthLoginService;
import com.meinil.common.cache.constants.CacheConstants;
import com.meinil.common.cache.utils.CacheUtil;
import com.meinil.common.core.domain.LoginUser;
import com.meinil.common.core.domain.R;
import com.meinil.common.core.utlis.BCryptUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.constants.WebConstants;
import com.meinil.common.web.exception.SparionException;
import com.meinil.common.web.properties.JwtProperties;
import com.meinil.common.web.utils.JwtUtil;
import com.meinil.common.web.utils.WebUtil;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.feign.UserFeignClient;
import com.meinil.common.core.domain.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final JwtProperties jwtProperties;

    private final AccountProperties accountProperties;

    private final UserFeignClient userFeignClient;

    private final AuthLoginConvert authLoginConvert;

    public AuthLoginServiceImpl(JwtProperties jwtProperties, AccountProperties accountProperties, UserFeignClient userFeignClient, AuthLoginConvert authLoginConvert) {
        this.jwtProperties = jwtProperties;
        this.accountProperties = accountProperties;
        this.userFeignClient = userFeignClient;
        this.authLoginConvert = authLoginConvert;
    }

    @Override
    public LoginVO login(PasswordLoginBody loginBody) {
        R<UserInfo> result = userFeignClient.getUserInfo(loginBody.getUsername());
        if (result.isFail()) {
            throw new RuntimeException(result.getMsg());
        }

        UserInfo userInfo = result.getData();
        if (Objects.isNull(userInfo)) {
            throw new SparionException("用户不存在, 请先注册");
        }

        // 1. 校验验证码
        if (AuthConstants.ENABLE_CAPTCHA_1.equals(accountProperties.getEnableCaptcha())) {
            String cacheCaptcha = CacheUtil.getCacheObject(CacheConstants.CAPTCHA_CODE_KEY + loginBody.getUuid());
            if (StringUtil.isBlank(cacheCaptcha)) {
                throw new SparionException("验证码不存在或已过期");
            }

            if (StringUtil.notEquals(cacheCaptcha, loginBody.getCode())) {
                throw new SparionException("验证码不正确");
            }
        }

        // 2. 校验密码
        Integer retryCount = CacheUtil.getCacheObject(CacheConstants.PWD_ERR_CNT_KEY + userInfo.getUserId());
        if (Objects.isNull(retryCount)) {
            retryCount = 0;
        }
        if (retryCount > accountProperties.getMaxRetryCount()) {
            throw new SparionException("此账号已超过最大重试次数, 请%s分钟后重试", accountProperties.getLockTime());
        }
        if (!BCryptUtil.checkPassword(loginBody.getPassword(), userInfo.getPassword())) {
            CacheUtil.setCacheObject(CacheConstants.PWD_ERR_CNT_KEY + userInfo.getUserId(), retryCount + 1, accountProperties.getLockTime(), TimeUnit.MINUTES);
            throw new SparionException("密码错误");
        }

        // 3. 创建token
        Map<String, Object> map = new HashMap<>() {{
            put("userId", userInfo.getUserId());
            put("username", userInfo.getUsername());
        }};
        String accessToken = JwtUtil.createToken(map);

        // 4. 用户信息缓存到redis
        LoginUser loginUser = authLoginConvert.userInfoTologinUser(userInfo);
        loginUser.setAccessToken(accessToken);
        loginUser.setExpireIn(JwtUtil.getClaims(accessToken, WebConstants.JWT_CLAIM_EXP, Long.class));
        String key = String.format("%s%s", CacheConstants.LOGIN_USER_KEY, userInfo.getUserId());
        CacheUtil.setCacheObject(key, loginUser, jwtProperties.getExpirationTime(), TimeUnit.MINUTES);

        // 5. 返回前端
        return authLoginConvert.loginUserToLoginVO(loginUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void register(RegisterBody registerBody) {
        RegisterDTO registerDTO = authLoginConvert.registerBodyToRegisterDTO(registerBody);
        registerDTO.setPassword(BCryptUtil.hashPassword(registerDTO.getPassword()));
        R<Boolean> result = userFeignClient.registerUserInfo(registerDTO);
        if (result.isFail()) {
            throw new SparionException("注册失败: " + result.getMsg());
        }
    }

    @Override
    public void logout() {
        String key = String.format("%s%s", CacheConstants.LOGIN_USER_KEY, WebUtil.getUserId());
        CacheUtil.deleteObject(key);
    }
}
