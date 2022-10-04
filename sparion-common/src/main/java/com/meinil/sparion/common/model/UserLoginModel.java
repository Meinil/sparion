package com.meinil.sparion.common.model;

import com.meinil.sparion.common.entity.Permission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
@Data
@ApiModel("用户登录vo类")
public class UserLoginModel {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("账号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phonenumber;

    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private String sex;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("权限")
    private List<Permission> permissions;

    @ApiModelProperty("角色")
    private List<String> roles;

    @ApiModelProperty("token")
    private String token;
}
