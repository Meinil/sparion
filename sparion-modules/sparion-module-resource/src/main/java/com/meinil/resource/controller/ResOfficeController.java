package com.meinil.resource.controller;

import com.meinil.common.core.domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meinil
 * @date 2025/4/15
 * @description office文件controller
 */
@RestController("/resource/office")
public class ResOfficeController {

    /**
     * 文件模板替换
     * @param variable
     * @return
     */
    @PostMapping("/replaceVariable")
    public R<String> replaceVariable(@RequestParam String variable) {
        return R.data("");
    }
}
