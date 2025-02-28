package com.meinil.common.web.interceptor;

import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.properties.SecurityProperties;
import com.meinil.common.web.utils.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description 仅允许添加了自定义请求头的客户端访问
 */
public class GatewayInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityProperties securityProperties = SpringUtil.getBean(SecurityProperties.class);
        String header = request.getHeader(securityProperties.getAllowHeader());
        if (StringUtil.isEmpty(securityProperties.getAllowHeader()) || StringUtil.isEmpty(securityProperties.getAllowHeaderValue())) {
            return true;
        }
        if (StringUtil.isEmpty(header) || !header.equals(securityProperties.getAllowHeaderValue())) {
            throw new RuntimeException("当前api仅能从gateway访问");
        }
        return true;
    }
}
