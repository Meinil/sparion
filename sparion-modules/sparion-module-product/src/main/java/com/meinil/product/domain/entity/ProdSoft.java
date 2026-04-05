package com.meinil.product.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.meinil.common.mybatis.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件信息实体
 */
@TableName("prod_soft")
public class ProdSoft extends BaseEntity {

    /**
     * 软件名称
     */
    private String name;

    /**
     * 软件编码
     */
    private String code;

    /**
     * 软件版本
     */
    private String version;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    /**
     * 关联文件id列表，逗号分隔
     */
    private String fileIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }
}
