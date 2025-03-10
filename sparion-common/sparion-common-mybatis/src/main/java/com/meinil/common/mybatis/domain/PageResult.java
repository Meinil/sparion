package com.meinil.common.mybatis.domain;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/6
 * @description
 */
public class PageResult<T> extends PageQuery {
    /**
     * 总页数
     */
    private Long pages;

    /**
     * 总数
     */
    private Long total;

    /**
     * 数据
     */
    private List<T> records;

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
