package com.example.apigatwayservice.config;


import com.example.apigatwayservice.filter.CustomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Slf4j
//@Configuration
public class FilterConfig {

// global 필터 적용 어떻게 하는지 추후 확인
//    @Bean
//    public RouteLocator getewayRoutes(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//                .route(r -> r.path("/first-service/**")
//                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
//                                .addResponseHeader("first-response", "first-response-header")
//                                .filter(this::customFilter)
//                        )
//                        .uri("http://localhost:8081"))
//                .route(r -> r.path("/second-service/**")
//                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
//                                .addResponseHeader("second-response", "second-response-header")
//                                .filter(this::customFilter)
//                        )
//                        .uri("http://localhost:8082"))
//                .build();
//    }
//
//    private Mono<Void> customFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        log.info("커스텀 PRE filter: request id -> {}", request.getId());
//
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            log.info("커스텀 POST filter: response id -> {}", response.getStatusCode());
//        }));
//    }
}
