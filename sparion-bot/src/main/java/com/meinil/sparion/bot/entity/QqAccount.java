package com.meinil.sparion.bot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.sparion.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Meinil
 * @date 2022/10/4
 * @description
 */
@Data
@TableName("bot_qq_account")
@EqualsAndHashCode(callSuper = true)
public class QqAccount extends BaseEntity {
    @ApiModelProperty("qq号")
    private Long qq;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("秘钥")
    private String secretKey;

    @ApiModelProperty("device.json")
    private String device;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
