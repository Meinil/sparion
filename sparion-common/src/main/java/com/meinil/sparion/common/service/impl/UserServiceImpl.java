package com.meinil.sparion.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.sparion.common.entity.LoginUser;
import com.meinil.sparion.common.entity.User;
import com.meinil.sparion.common.mapper.UserMapper;
import com.meinil.sparion.common.model.UserLoginModel;
import com.meinil.sparion.common.result.SparionException;
import com.meinil.sparion.common.service.IUserService;
import com.meinil.sparion.common.utils.JwtUtils;
import com.meinil.sparion.common.utils.RedisCache;
import com.meinil.sparion.common.vo.UserLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserLoginModel login(UserLoginVO userLoginVO) {
        // 校验用户名密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginVO.getUsername(), userLoginVO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (authentication == null) {
            throw new SparionException("校验失败");
        }

        // 生成token
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String token = jwtUtils.getJwtToken(loginUser.getId().toString());

        // 设置返回的信息
        UserLoginModel userLoginModel = new UserLoginModel();
        BeanUtils.copyProperties(loginUser.getUser(), userLoginModel);
        userLoginModel.setToken(token);
        userLoginModel.setPermissions(loginUser.getPermissions());
        userLoginModel.setRoles(loginUser.getRoles());

        // 将用户信息放入redis
        redisCache.setCacheObject("login:" + loginUser.getId(), loginUser, jwtUtils.getExpire(), TimeUnit.SECONDS);
        return userLoginModel;
    }

    @Override
    public Boolean logout() {
        LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return redisCache.deleteObject("login:" + user.getId());
    }
}
