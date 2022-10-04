package com.meinil.sparion.bot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Meinil
 * @date 2022/10/4
 * @description
 */
@Data
@ApiModel("机器人创建Vo类")
public class QqAccountCreateVo {
    @ApiModelProperty("qq号")
    private Long qq;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("device.json")
    private String device;

    @ApiModelProperty("备注")
    private String remark;
}
