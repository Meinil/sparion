package com.meinil.sparion.bot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.sparion.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
@Data
@TableName("bot_qq_account")
@EqualsAndHashCode(callSuper = true)
@ApiModel("机器人实体类")
public class BotInfo extends BaseEntity {
    @ApiModelProperty("qq,id")
    private Long qqAccountId;
}
