package com.example.apigateway.config;


import com.example.apigateway.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {

    private final AuthFilter authenticationFilter;

    @Autowired
    public RouteConfig(AuthFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("CHOICE A ROUTE ID",p -> p
                        .path("/books/**")
                        .filters(f -> f
                                .filter(authenticationFilter)
                                .stripPrefix(1))
                        .uri("http://localhost:8083"))
                .build();
    }

}