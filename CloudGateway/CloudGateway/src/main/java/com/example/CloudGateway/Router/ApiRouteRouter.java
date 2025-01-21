package com.example.CloudGateway.Router;

import com.example.CloudGateway.Handler.ApiRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration 
public class ApiRouteRouter {
    @Bean
    public RouterFunction<ServerResponse> route(ApiRouteHandler apiRouteHandler) {
        return RouterFunctions.route()
                .POST("/routes", apiRouteHandler::create)
                .GET("/routes/{routeId}", apiRouteHandler::getById)
                .POST("/routes/refresh-routes", apiRouteHandler::refreshRoutes) // Ensure this matches your handler method
                .build();
    }
}