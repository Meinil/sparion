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
 * @description 权限实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_perms")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6544261725057191820L;

    private String permsName;

    private String path;

    private String component;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private String remark;
}
