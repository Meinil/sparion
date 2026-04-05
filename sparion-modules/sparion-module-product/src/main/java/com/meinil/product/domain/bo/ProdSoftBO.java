package com.meinil.product.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件查询/新增对象
 */
public class ProdSoftBO {

    /**
     * 软件名称
     */
    @NotBlank(message = "软件名称不能为空")
    private String name;

    /**
     * 软件编码
     */
    @NotBlank(message = "软件编码不能为空")
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
    @NotBlank(message = "关联文件不能为空")
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
