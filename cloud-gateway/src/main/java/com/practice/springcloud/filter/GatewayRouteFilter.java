package com.practice.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author michael.zhang
 * @date 5/19/2020 1:25 PM
 * @desc 自定义gateway filter， 实现 GlobalFilter和Ordered 接口
 */
@Slf4j
@Component
public class GatewayRouteFilter implements GlobalFilter, Ordered {
    /**
     * @param exchange 可以从exchange中获取request，response等
     * @param chain 整个filter链，可将exchange处理完后传入chain，到下一个filter进行处理
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入gatewayRouteFilter。。。");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(username == null){
            log.info("username is null");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * getOrder() 用于定义filter的优先级，值越大优先级越低
     * -2147483648~2147483647
     *
     * @return 此filter的顺序
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
