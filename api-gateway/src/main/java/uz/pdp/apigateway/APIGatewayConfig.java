package uz.pdp.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){
         return locatorBuilder.routes()
                 .route(predicateSpec -> predicateSpec
                         .path("/get")
                         .filters(f -> f.addRequestHeader("myHeader",  "Vola"))
                         .uri("http://httpbin.org:80")
                 )
                .route(predicateSpec -> predicateSpec
                        .path("/currency-conversion/**")
                        .filters(f -> f.addRequestHeader("myHeader",  "Vola"))
                        .uri("lb://currency-conversion")
                ).build();
    }
}
