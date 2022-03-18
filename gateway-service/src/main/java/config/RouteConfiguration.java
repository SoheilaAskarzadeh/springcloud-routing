package config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder){
       return builder.routes()
                .route(r-> r.path("/service/v1/first/**")
                        .filters(f-> f.addRequestHeader("first-request","first").
                                rewritePath("/second/(?.*)","/${}"))
                            .uri("http://localhost:8001"))

                .route(r-> r.path("/service/v1/second/**")
                            .uri("http://localhost:8002"))

                .build();
    }
}
