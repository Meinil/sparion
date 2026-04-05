package com.meinil.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.common.mybatis.utils.PageUtil;
import com.meinil.common.web.exception.SparionException;
import com.meinil.product.convert.ProdSoftConvert;
import com.meinil.product.domain.bo.ProdSoftBO;
import com.meinil.product.domain.bo.ProdSoftEditBO;
import com.meinil.product.domain.entity.ProdSoft;
import com.meinil.product.domain.vo.ProdSoftVO;
import com.meinil.product.mapper.ProdSoftMapper;
import com.meinil.product.service.IProdSoftService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件信息 Service 实现
 */
@Service
public class ProdSoftServiceImpl implements IProdSoftService {

    private final ProdSoftMapper baseMapper;

    private final ProdSoftConvert prodSoftConvert;

    public ProdSoftServiceImpl(ProdSoftMapper baseMapper, ProdSoftConvert prodSoftConvert) {
        this.baseMapper = baseMapper;
        this.prodSoftConvert = prodSoftConvert;
    }

    @Override
    public PageResult<ProdSoftVO> list(ProdSoftBO prodSoftBO, PageQuery pageQuery) {
        IPage<ProdSoft> page = baseMapper.selectList(prodSoftBO, PageUtil.pageOf(pageQuery));
        return PageUtil.pageToPageResult(page, prodSoftConvert::prodSoftToProdSoftVO);
    }

    @Override
    public Long add(ProdSoftBO prodSoftBO) {
        validateCodeUnique(prodSoftBO.getCode(), null);
        ProdSoft prodSoft = new ProdSoft();
        prodSoft.setName(prodSoftBO.getName());
        prodSoft.setCode(prodSoftBO.getCode());
        prodSoft.setVersion(prodSoftBO.getVersion());
        prodSoft.setPublishTime(prodSoftBO.getPublishTime());
        prodSoft.setFileIds(normalizeFileIds(prodSoftBO.getFileIds()));
        baseMapper.insert(prodSoft);
        return prodSoft.getId();
    }

    @Override
    public Integer edit(ProdSoftEditBO prodSoftEditBO) {
        ProdSoft current = baseMapper.selectById(prodSoftEditBO.getId());
        if (Objects.isNull(current)) {
            throw new SparionException("软件不存在");
        }
        validateCodeUnique(prodSoftEditBO.getCode(), prodSoftEditBO.getId());
        ProdSoft prodSoft = new ProdSoft();
        prodSoft.setId(prodSoftEditBO.getId());
        prodSoft.setName(prodSoftEditBO.getName());
        prodSoft.setCode(prodSoftEditBO.getCode());
        prodSoft.setVersion(prodSoftEditBO.getVersion());
        prodSoft.setPublishTime(prodSoftEditBO.getPublishTime());
        prodSoft.setFileIds(normalizeFileIds(prodSoftEditBO.getFileIds()));
        return baseMapper.updateById(prodSoft);
    }

    @Override
    public Integer remove(Long id) {
        return baseMapper.deleteById(id);
    }

    private void validateCodeUnique(String code, Long currentId) {
        ProdSoft exists = baseMapper.selectByCode(code);
        if (Objects.nonNull(exists) && !Objects.equals(exists.getId(), currentId)) {
            throw new SparionException("软件编码已存在");
        }
    }

    private String normalizeFileIds(String fileIds) {
        List<String> normalizedIds = Arrays.stream(fileIds.split(","))
                .map(String::trim)
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList());
        if (normalizedIds.isEmpty()) {
            throw new SparionException("请至少上传1个关联文件");
        }
        if (normalizedIds.size() > 5) {
            throw new SparionException("关联文件最多上传5个");
        }
        boolean invalidId = normalizedIds.stream().anyMatch(item -> !item.matches("\\d+"));
        if (invalidId) {
            throw new SparionException("关联文件格式不正确");
        }
        return String.join(",", normalizedIds);
    }
}
