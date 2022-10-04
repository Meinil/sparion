package com.meinil.sparion.common.handler;

import com.alibaba.fastjson2.JSON;
import com.meinil.sparion.common.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description 鉴权失败
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R<Object> result = R.error()
                .code(HttpStatus.FORBIDDEN.value())
                .msg("权限不足");

        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try(PrintWriter writer = response.getWriter()){
            writer.print(JSON.toJSONString(result));
        }
    }
}
