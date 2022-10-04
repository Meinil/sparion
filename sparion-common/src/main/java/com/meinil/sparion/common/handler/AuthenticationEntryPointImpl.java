package com.meinil.sparion.common.handler;

import com.alibaba.fastjson2.JSON;
import com.meinil.sparion.common.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description SpringSecurity 认证失败的处理方法
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R<Object> result = R.error()
                .code(HttpStatus.UNAUTHORIZED.value())
                .msg("认证失败,请重新登录");

        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try(PrintWriter writer = response.getWriter()){
            writer.print(JSON.toJSONString(result));
        }
    }
}
