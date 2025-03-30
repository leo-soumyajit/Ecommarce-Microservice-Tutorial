package com.soumyajit.Api.Gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingOrdersFilters extends AbstractGatewayFilterFactory<LoggingOrdersFilters.Config> {

    public LoggingOrdersFilters() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                log.info("Order Filter Pre: {}",exchange.getRequest().getURI());
                return chain.filter(exchange).then(Mono.fromRunnable(new Runnable() {
                    @Override
                    public void run() {
                        log.info("Order Filter Pre: {}",exchange.getResponse().getStatusCode());
                    }
                }));
            }
        };
    }

    public static class Config{

    }
}
