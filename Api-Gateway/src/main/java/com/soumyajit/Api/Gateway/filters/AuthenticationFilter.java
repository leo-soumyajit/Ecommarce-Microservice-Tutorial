package com.soumyajit.Api.Gateway.filters;

import com.soumyajit.Api.Gateway.Service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Configs> {


    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        super(Configs.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Configs config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

                //if(!config.isEnabled)return chain.filter(exchange);

                if(authorizationHeader == null){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                String token = authorizationHeader.split("Bearer ")[1];
                Long userId = jwtService.getUserIdFromToken(token);

                exchange.getRequest()
                        .mutate()
                        .header("X-User-Id",userId.toString())
                        .build();

                return chain.filter(exchange);
            }
        };
    }

    public static class Configs{
        private boolean isEnabled;

    }

}
