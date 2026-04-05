package com.meinil.product.service;

import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.product.domain.bo.ProdSoftBO;
import com.meinil.product.domain.bo.ProdSoftEditBO;
import com.meinil.product.domain.vo.ProdSoftVO;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件信息 Service
 */
public interface IProdSoftService {

    /**
     * 软件分页查询
     * @param prodSoftBO 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    PageResult<ProdSoftVO> list(ProdSoftBO prodSoftBO, PageQuery pageQuery);

    /**
     * 新增软件
     * @param prodSoftBO 软件信息
     * @return 软件id
     */
    Long add(ProdSoftBO prodSoftBO);

    /**
     * 修改软件
     * @param prodSoftEditBO 软件信息
     * @return 影响行数
     */
    Integer edit(ProdSoftEditBO prodSoftEditBO);

    /**
     * 删除软件
     * @param id 软件id
     * @return 影响行数
     */
    Integer remove(Long id);
}
