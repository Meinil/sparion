package com.meinil.sparion.common.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 实体类基类
 */
@Data
@ApiModel("实体类基类")
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("id")
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @JSONField(serialize = false)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(serialize = false)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    @JSONField(serialize = false)
    @ApiModelProperty("创建用户的id")
    private Long createUserId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(serialize = false)
    @ApiModelProperty("更新用户的id")
    private Long updateUserId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @JSONField(serialize = false)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;
}
