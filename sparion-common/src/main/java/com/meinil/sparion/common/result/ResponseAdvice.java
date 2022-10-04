package com.meinil.sparion.common.result;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description 统一返回结果
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 自定义注解校验 - 校验方法上的注解
        ApiResult annotation = returnType.getMethodAnnotation(ApiResult.class);
        if (annotation != null) {
            return annotation.pack();
        }

        // 校验类上的注解
        Class<?> clazz = returnType.getDeclaringClass();
        RestController restController = clazz.getDeclaredAnnotation(RestController.class);
        if (restController == null) {
            return false;
        }

        // 自定义注解校验 - 校验类上的注解
        ApiResult apiResult = clazz.getDeclaredAnnotation(ApiResult.class);
        if (apiResult != null) {
            return apiResult.pack();
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // R对象不需要包装
        if (body instanceof R) {
            return body;
        }

        // 如果是字符串SpringBoot会自动返回,手动包装一下
        if (body instanceof String) {
            return JSON.toJSONString(R.success().data(body));
        }

        // 获取注解
        ApiResult apiResult = returnType.getMethodAnnotation(ApiResult.class);
        R<Object> result = R.custom()
                .code(200)
                .msg("操作成功");

        if (apiResult != null) {
            if (apiResult.code() != -1) {
                result.setCode(apiResult.code());
            }

            if (!"".equals(apiResult.msg())) {
                result.setMsg(apiResult.msg());
            }
        }

        // body不为空
        if (body != null) {
            result.setData(body);
        }

        return result;
    }
}
