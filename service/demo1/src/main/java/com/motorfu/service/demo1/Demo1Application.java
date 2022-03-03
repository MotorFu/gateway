package com.motorfu.service.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/4 13:39
 */

@SpringBootApplication
public class Demo1Application {

  public static void main(String[] args) {
    SpringApplication.run(Demo1Application.class, args);
  }

  private WebClient webClient;

  @Bean
  public RouterFunction<ServerResponse> testFunRouterFunction() {
    webClient = WebClient.create("http://localhost:9999");
    RestTemplate restTemplate = new RestTemplate();
    return RouterFunctions.route(
      RequestPredicates.path("/demo1"),
      request -> ServerResponse.ok().body(BodyInserters.fromValue("hello demo1")))
      .and(RouterFunctions.route(RequestPredicates.path("/demo1/test1"), request -> {

        return Mono.just(Optional.ofNullable(restTemplate.getForObject("http://localhost:8002/demo2",String.class)).orElse("demo2 failed"))
          .doOnSuccess(System.out::println)
          .then(ServerResponse.ok().body(BodyInserters.fromValue("hello demo1 test1")));

//        return webClient.get().uri("/demo2").retrieve()
//          .bodyToMono(String.class).timeout(Duration.of(10, ChronoUnit.SECONDS))
//          .doOnSuccess(System.out::println)
//          .then(ServerResponse.ok().body(BodyInserters.fromValue("hello demo1 test1")));
      }))
      .and(RouterFunctions.route().GET("/demo1/test2", response -> {
        return ServerResponse.ok().body(BodyInserters.fromValue("hello demo1 test2"));
      }).build())
      ;
  }
}
