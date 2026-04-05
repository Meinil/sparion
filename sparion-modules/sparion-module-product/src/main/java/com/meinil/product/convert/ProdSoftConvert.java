package com.meinil.product.convert;

import com.meinil.product.domain.entity.ProdSoft;
import com.meinil.product.domain.vo.ProdSoftVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件转换类
 */
@Mapper
public interface ProdSoftConvert {

    List<ProdSoftVO> prodSoftToProdSoftVO(List<ProdSoft> softs);
}
