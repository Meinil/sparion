package com.meinil.sparion.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meinil.sparion.common.entity.LoginUser;
import com.meinil.sparion.common.entity.User;
import com.meinil.sparion.common.mapper.PermissionMapper;
import com.meinil.sparion.common.mapper.RoleMapper;
import com.meinil.sparion.common.mapper.UserMapper;
import com.meinil.sparion.common.result.SparionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permsMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private Executor executor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. 根据用户名获取数据库中的用户
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username);

        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new SparionException("用户名不存在");
        }

        CompletableFuture<LoginUser> future = CompletableFuture.supplyAsync(() -> {
            // 2. 查询权限信息
            return permsMapper.queryPermsByUserId(user.getId());
        }, executor).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            // 3. 查询角色信息
            return roleMapper.queryRolesByUserId(user.getId())
                    .stream()
                    .map(role -> role.getKey())
                    .collect(Collectors.toList());
        }, executor), (permissions, roleList) -> {
            return new LoginUser(user, permissions, roleList);
        }, executor).exceptionally(e -> {
            throw new RuntimeException("查询异常");
        });

        // 4. 返回UserDetail
        return future.join();
    }
}
