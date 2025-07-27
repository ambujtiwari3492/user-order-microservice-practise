package com.example.apigateway.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

            // Route to user-service
            .route("user-service", r -> r
                .path("/users/**") // All requests to /user/** will be routed
                .uri("lb://USER-SERVICE") // lb = load balanced via Eureka
            )

            // Add more routes here...

            .build();
    }
}
