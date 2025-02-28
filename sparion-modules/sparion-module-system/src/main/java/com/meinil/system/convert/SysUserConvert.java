package com.meinil.system.convert;

import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import com.meinil.system.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@Mapper
public interface SysUserConvert {

    @Mapping(source = "id", target = "userId")
    LoginUser sysUserToLoginUser(SysUser user);

    SysUser registerDTOToSysUser(RegisterDTO registerDTO);
}
