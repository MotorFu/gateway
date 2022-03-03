package com.motorfu.gateway.server;

import com.motorfu.gateway.core.GatewayCoreAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/3 16:15
 */
@SpringBootApplication(scanBasePackageClasses = {
  GatewayCoreAutoConfigure.class,
  GatewayApplication.class
})
public class GatewayApplication {
  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

    return builder.routes().route(r -> r.order(-1)
      .host("**.postman-echo.com").and().path("/**")
      .uri("http://httpbin.org/")
    )
      .build();
  }

  @Bean
  public RouterFunction<ServerResponse> testFunRouterFunction() {
    RouterFunction<ServerResponse> route = RouterFunctions.route(
      RequestPredicates.path("/testfun"),
      request -> ServerResponse.ok().body(BodyInserters.fromValue("hello")));
    return route;
  }

  public static final String HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS = "hello from fake /actuator/metrics/gateway.requests";

  @Bean
  public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
    RouterFunction<ServerResponse> route = RouterFunctions.route(
      RequestPredicates.path("/actuator/metrics/gateway.requests"),
      request -> ServerResponse.ok().body(BodyInserters
        .fromValue(HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS)));
    return route;
  }

  static class Hello {

    String message;

    Hello() {
    }

    Hello(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }
}
