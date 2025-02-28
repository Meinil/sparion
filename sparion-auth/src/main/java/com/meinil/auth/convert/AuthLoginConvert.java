package com.meinil.auth.convert;

import com.meinil.auth.form.RegisterBody;
import com.meinil.common.web.domain.UserInfo;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.system.api.model.LoginUser;
import org.mapstruct.Mapper;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@Mapper
public interface AuthLoginConvert {

    UserInfo loginUserToUserInfo(LoginUser loginUser);

    RegisterDTO registerBodyToRegisterDTO(RegisterBody registerBody);
}
