package com.meinil.resource.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.common.mybatis.domain.BaseEntity;

/**
 * @author meinil
 * @date 2025/4/15
 * @description 文件模板
 */
@TableName("res_template")
public class ResTemplate extends BaseEntity {

    /**
     * 模板编码
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 文件id
     */
    private Long fileId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
