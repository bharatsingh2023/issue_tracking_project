package com.gatway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ISSUE-LIST", r -> r.path("/issueList/**")
                        .uri("lb://ISSUE-LIST"))
                .route("ADDPROJECT", r -> r.path("/addNewProject/**")
                        .uri("lb://ADDPROJECT"))
                .route("ISSUEDETAILS", r -> r.path("/issueDetails/**")
                        .uri("lb://ISSUEDETAILS"))
                .route("USERLOGIN", r -> r.path("/userLogin/**")
                        .uri("lb://USERLOGIN"))
                .build();
    }
}
