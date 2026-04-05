package com.meinil.resource.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Meinil
 * @date 2026/3/31
 * @description 文件元数据返回对象
 */
public class ResFileVO {

    /**
     * 文件id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * 文件大小
     */
    private Long fileSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
