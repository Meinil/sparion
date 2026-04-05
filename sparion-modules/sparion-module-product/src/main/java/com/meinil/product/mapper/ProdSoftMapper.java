package com.meinil.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meinil.product.domain.bo.ProdSoftBO;
import com.meinil.product.domain.entity.ProdSoft;
import org.apache.ibatis.annotations.Param;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件信息 Mapper
 */
public interface ProdSoftMapper extends BaseMapper<ProdSoft> {

    /**
     * 软件分页查询
     * @param query 查询条件
     * @param page 分页对象
     * @return 分页结果
     */
    IPage<ProdSoft> selectList(@Param("query") ProdSoftBO query, IPage<ProdSoft> page);

    /**
     * 根据编码查询软件
     * @param code 软件编码
     * @return 软件
     */
    ProdSoft selectByCode(@Param("code") String code);
}
