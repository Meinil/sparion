package com.meinil.product.controller;

import com.meinil.common.core.domain.R;
import com.meinil.common.mybatis.domain.PageQuery;
import com.meinil.common.mybatis.domain.PageResult;
import com.meinil.product.domain.bo.ProdSoftBO;
import com.meinil.product.domain.bo.ProdSoftEditBO;
import com.meinil.product.domain.vo.ProdSoftVO;
import com.meinil.product.service.IProdSoftService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meinil
 * @date 2026/3/31
 * @description 软件信息 Controller
 */
@RestController
@RequestMapping("/product/softwares")
public class ProdSoftController {

    private final IProdSoftService prodSoftService;

    public ProdSoftController(IProdSoftService prodSoftService) {
        this.prodSoftService = prodSoftService;
    }

    @GetMapping
    public R<PageResult<ProdSoftVO>> list(ProdSoftBO prodSoftBO, PageQuery pageQuery) {
        return R.data(prodSoftService.list(prodSoftBO, pageQuery));
    }

    @PostMapping
    public R<String> add(@RequestBody @Validated ProdSoftBO prodSoftBO) {
        Long id = prodSoftService.add(prodSoftBO);
        return R.data(String.valueOf(id));
    }

    @PutMapping("/{id}")
    public R<Integer> edit(@PathVariable(name = "id") Long id, @RequestBody @Validated ProdSoftEditBO prodSoftEditBO) {
        prodSoftEditBO.setId(id);
        return R.data(prodSoftService.edit(prodSoftEditBO));
    }

    @DeleteMapping("/{id}")
    public R<Integer> remove(@PathVariable(name = "id") Long id) {
        return R.data(prodSoftService.remove(id));
    }
}
