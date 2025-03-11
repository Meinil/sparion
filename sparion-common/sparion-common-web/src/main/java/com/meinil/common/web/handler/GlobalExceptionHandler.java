package com.meinil.common.web.handler;

import com.meinil.common.core.constants.HttpStatusConstant;
import com.meinil.common.core.domain.R;
import com.meinil.common.web.exception.SparionException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生参数校验异常.", requestURI, e);
        return R.fail(e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
    }


    @ExceptionHandler(SignatureException.class)
    public R<Void> handleSignatureException(SignatureException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',非法token.", requestURI, e);
        return R.fail(HttpStatusConstant.UNAUTHORIZED, "非法token");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public R<Void> handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',登录过期.", requestURI, e);
        return R.fail(HttpStatusConstant.FORBIDDEN, "登录过期");
    }

    @ExceptionHandler(SparionException.class)
    public R<Void> handleSparionException(SparionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生业务异常.", requestURI, e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.fail(e.getMessage());
    }
}
