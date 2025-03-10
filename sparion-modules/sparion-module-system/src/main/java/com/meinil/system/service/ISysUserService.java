package com.meinil.system.service;

import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.common.core.domain.UserInfo;
import com.meinil.system.domain.bo.SysUserBO;
import com.meinil.system.domain.vo.SysUserVO;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
public interface ISysUserService {

    UserInfo getLoginUser(String username);

    Boolean register(RegisterDTO registerDTO);

    PageResult<SysUserVO> list(SysUserBO sysUserBO, PageQuery pageQuery);
}
