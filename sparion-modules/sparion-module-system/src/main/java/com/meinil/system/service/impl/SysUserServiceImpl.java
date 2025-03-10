package com.meinil.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.common.mybatis.utils.PageUtil;
import com.meinil.common.web.exception.SparionException;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.common.core.domain.UserInfo;
import com.meinil.system.convert.SysUserConvert;
import com.meinil.system.domain.bo.SysUserBO;
import com.meinil.system.domain.entity.SysUser;
import com.meinil.system.domain.vo.SysUserVO;
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

    private final SysUserMapper baseMapper;

    private final SysUserConvert userConvert;

    public SysUserServiceImpl(SysUserMapper baseMapper, SysUserConvert userConvert) {
        this.baseMapper = baseMapper;
        this.userConvert = userConvert;
    }

    @Override
    public UserInfo getLoginUser(String username) {
        SysUser user = baseMapper.selectUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new SparionException("用户不存在");
        }
        return userConvert.sysUserToLoginUser(user);
    }

    @Override
    public Boolean register(RegisterDTO registerDTO) {
        SysUser tempUser = baseMapper.selectUserByUserName(registerDTO.getUsername());
        if (Objects.nonNull(tempUser)) {
            throw new SparionException("用户已存在");
        }
        SysUser user = userConvert.registerDTOToSysUser(registerDTO);
        return baseMapper.insertUser(user) > 0;
    }

    @Override
    public PageResult<SysUserVO> list(SysUserBO sysUserBO, PageQuery pageQuery) {
        IPage<SysUser> page = baseMapper.selectList(sysUserBO, PageUtil.pageOf(pageQuery));
        return PageUtil.pageToPageResult(page, userConvert::sysUserToSysUserVO);
    }
}
