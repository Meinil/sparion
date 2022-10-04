package com.meinil.sparion.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
@Data
@ApiModel("用户登录vo类")
public class UserLoginVO implements Serializable {

    private static final long serialVersionUID = -1311831408805870343L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;
}
