package com.meinil.auth.convert;

import com.meinil.auth.domain.vo.LoginVO;
import com.meinil.auth.form.RegisterBody;
import com.meinil.system.api.dto.RegisterDTO;
import com.meinil.common.core.domain.LoginUser;
import com.meinil.common.core.domain.UserInfo;
import org.mapstruct.Mapper;

/**
 * @author Meinil
 * @date 2025/2/27
 * @description
 */
@Mapper
public interface AuthLoginConvert {

    LoginUser userInfoTologinUser(UserInfo userInfo);

    RegisterDTO registerBodyToRegisterDTO(RegisterBody registerBody);

    LoginVO loginUserToLoginVO(LoginUser loginUser);
}
