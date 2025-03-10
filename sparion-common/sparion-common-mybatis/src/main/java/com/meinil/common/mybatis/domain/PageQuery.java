package com.meinil.common.mybatis.domain;

/**
 * @author Meinil
 * @date 2025/3/6
 * @description 分页对象
 */
public class PageQuery {

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 当前页
     */
    private Long current;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
