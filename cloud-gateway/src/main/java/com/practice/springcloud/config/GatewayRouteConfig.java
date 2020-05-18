//package com.practice.springcloud.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author michael.zhang
// * @date 5/18/2020 4:59 PM
// * @desc
// */
//@Configuration
//public class GatewayRouteConfig {
//    /**
//     * java配置的方式配置路由
//     * @param routeLocatorBuilder
//     * @return routes
//     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//
//        routes.route("payment8001route",
//                r -> r.path("/payment")
//                        .uri("http://localhost:8001/payment")).build();
//
//        routes.route("payment8001route1",
//                r -> r.path("/payment/openfeign/timeout")
//                        .uri("http://localhost:8001/payment/openfeign/timeout")).build();
//
//        return routes.build();
//    }
//}
