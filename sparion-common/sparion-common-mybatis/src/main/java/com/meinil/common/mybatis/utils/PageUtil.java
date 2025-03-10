package com.meinil.common.mybatis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author Meinil
 * @date 2025/3/7
 * @description 分页工具类
 */
public class PageUtil {
    private PageUtil() {}

    /**
     * IPage 转为 PageResult对象
     * @param page page对象
     * @param converter 实体转换
     * @return PageResult 转换后的实体
     * @param <T> 入参
     * @param <R> 出参
     */
    public static <T, R> PageResult<R> pageToPageResult(IPage<T> page, Function<List<T>, List<R>> converter) {
        if (page == null) {
            return null;
        }
        PageResult<R> result = new PageResult<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setRecords(converter.apply(page.getRecords()));
        return result;
    }

    /**
     * 获取分页对象
     * @param query query查询对象 必须是PageQuery的子类
     * @return IPage对象
     * @param <T> 入参
     * @param <R> 出参
     */
    public static <T extends PageQuery, R> IPage<R> pageOf(T query) {
        return Page.of(query.getCurrent(), query.getSize());
    }
}
