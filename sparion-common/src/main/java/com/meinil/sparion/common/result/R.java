package com.meinil.sparion.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -7074082293029934144L;

    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "承载数据")
    private T data;

    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;

    public R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> R<T> success() {
        return success(200, null,"操作成功");
    }

    public static <T> R<T> success(Integer code) {
        return success(code, null, "操作成功");
    }

    public static <T> R<T> success(String msg) {
        return success(200, null, msg);
    }

    public static <T> R<T> success(T data) {
        return success(200, data, "操作成功");
    }

    public static <T> R<T> success(Integer code, T data, String msg) {
        return new R<>(code, data, msg);
    }


    public static <T> R<T> error() {
        return error("操作失败");
    }
    public static <T> R<T> error(String msg) {
        return new R<>(400, null, msg);
    }
    public static <T> R<T> custom() {
        return new R<>();
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }
}
