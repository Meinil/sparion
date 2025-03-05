package com.meinil.common.web.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Meinil
 * @date 2025/3/4
 * @description
 */
public class SparionException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -6213131202228665481L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public SparionException() {
    }

    public SparionException(Throwable cause) {
        super(cause);
    }

    public SparionException(String message) {
        this.message = message;
    }

    public SparionException(String format, Object ...args) {
        this.message = String.format(format, args);
    }

    public SparionException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

    public SparionException setMessage(String message) {
        this.message = message;
        return this;
    }

    public SparionException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
