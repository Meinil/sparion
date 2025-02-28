package com.meinil.system.service.impl;

import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import com.meinil.system.convert.SysUserConvert;
import com.meinil.system.domain.entity.SysUser;
import com.meinil.system.mapper.SysUserMapper;
import com.meinil.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper userMapper;

    private final SysUserConvert userConvert;

    public SysUserServiceImpl(SysUserMapper sysUserMapper, SysUserConvert userConvert) {
        this.userMapper = sysUserMapper;
        this.userConvert = userConvert;
    }

    @Override
    public LoginUser getLoginUser(String username) {
        SysUser user = userMapper.selectUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        return userConvert.sysUserToLoginUser(user);
    }

    @Override
    public Boolean register(RegisterDTO registerDTO) {
        SysUser tempUser = userMapper.selectUserByUserName(registerDTO.getUsername());
        if (Objects.nonNull(tempUser)) {
            throw new RuntimeException("用户已存在");
        }
        SysUser user = userConvert.registerDTOToSysUser(registerDTO);
        return userMapper.insertUser(user) > 0;
    }
}
