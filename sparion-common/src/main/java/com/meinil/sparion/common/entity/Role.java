package com.meinil.sparion.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 角色实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3435857998077459899L;

    private String name;

    private String key;

    private String status;

    private String remark;
}
