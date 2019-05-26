package clustercamp.user.config;

import clustercamp.user.api.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@EnableWebFlux
public class RouteConfiguraion implements WebFluxConfigurer {

  @Bean
  public RouterFunction<ServerResponse> routes(UserHandler handler) {

    return RouterFunctions.route(GET("/rx/user")
      .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::detail);
  }

}
