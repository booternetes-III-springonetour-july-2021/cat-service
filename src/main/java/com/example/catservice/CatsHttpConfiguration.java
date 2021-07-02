package com.example.catservice;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;


/**
 * @author Madhura Bhave
 * @author Josh Long
 */
//@Configuration
class CatsHttpConfiguration {

    @Bean
    RouterFunction<ServerResponse> http(CatsService service) {
        return route()
                .GET("/cats/{name}", r -> ok().body(service.getCat(r.pathVariable("name"))))
                .build();
    }
}
