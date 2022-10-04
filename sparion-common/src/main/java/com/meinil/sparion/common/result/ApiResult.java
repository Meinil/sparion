package com.meinil.sparion.common.result;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResult {
    /**
     * 是否进行包装
     */
    boolean pack() default true;

    /**
     * 响应码
     */
    int code() default -1;

    /**
     * 响应信息
     */
    String msg() default "";
}
