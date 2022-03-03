package com.motorfu.service.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/4 13:39
 */
@SpringBootApplication
public class Demo2Application {

  public static void main(String[] args) {
    SpringApplication.run(Demo2Application.class, args);
  }

  @Bean
  public RouterFunction<ServerResponse> testFunRouterFunction() {
    RouterFunction<ServerResponse> route = RouterFunctions.route(
      RequestPredicates.path("/demo2"),
      request -> ServerResponse.ok().body(BodyInserters.fromValue("hello demo2")));
    return route;
  }
}