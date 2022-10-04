package com.meinil.sparion.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Meinil
 * @date 2022/10/2
 * @description 自定义异常
 */

@NoArgsConstructor
@AllArgsConstructor
public class SparionException extends RuntimeException{
    @ApiModelProperty("异常状态码")
    private Integer code;

    public SparionException(String message) {
        super(message);
        this.code = 400;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
