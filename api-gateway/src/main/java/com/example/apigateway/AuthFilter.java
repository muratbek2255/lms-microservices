package com.example.apigateway;

import com.example.apigateway.config.RouteValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;


@Component
@Slf4j
public class AuthFilter implements GatewayFilter {

    private final WebClient.Builder webClient;

    private final RouteValidator validator;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthFilter(WebClient.Builder webClient, RouteValidator validator, JwtUtil jwtUtil) {
        this.webClient = webClient;
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if (validator.isSecured.test(exchange.getRequest())) {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization information");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }
            try {

                webClient.build()
                        .post()
                        .uri("http://service-users/users/validateToken?token=" + parts[1])
                        .retrieve().bodyToMono(String.class)
                        .map(userDto -> {
                            exchange.getRequest()
                                    .mutate()
                                    .header("X-auth-user-id", String.valueOf(parts[1]));
                            return exchange;
                        }).flatMap(chain::filter);
                jwtUtil.validateToken(authHeader);

            } catch (Exception e) {
                System.out.println("invalid access...!");
                throw new RuntimeException("un authorized access to application");
            }
        }
        return chain.filter(exchange);
    }
}