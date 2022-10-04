package com.meinil.sparion.common.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Meinil
 * @date 2022/9/26
 * @description 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ResponseAdviceException {
    @ExceptionHandler(value = Exception.class)
    public R<Void> hadndle(Exception e){
        log.error("", e);
        e.printStackTrace();
        return R.error(e.getMessage());
    }

    @ExceptionHandler(value = SparionException.class)
    public R<Object> handle(SparionException e) {
        log.error("", e);
        e.printStackTrace();
        return R.error(e.getMessage())
                .code(e.getCode());
    }
}
