package com.meinil.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Meinil
 * @date 2025/2/24
 * @description 授权验证
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private final static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 前置逻辑
        long start = System.currentTimeMillis();

        return chain.filter(exchange).doFinally(result -> {
            long end = System.currentTimeMillis();
            log.info("请求: 【{}】, 开始时间: {}, 结束时间: {}, 耗时: {}", exchange.getRequest().getURI(), start, end, end - start);
        });
    }
}
