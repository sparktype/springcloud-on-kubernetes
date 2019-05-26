package clustercamp.security.service;

import clustercamp.base.dto.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BaseUserDetailsService implements ReactiveUserDetailsService {

  private final WebClient webClient;

  private final UserMapper mapper;

  public BaseUserDetailsService(WebClient.Builder builder, UserMapper mapper) {
    webClient = builder.baseUrl("http://API-USER-SERVICE")
      .defaultHeader(HttpHeaders.USER_AGENT, "ClusterCampOAuth2.0")
      .build();
    this.mapper = mapper;
  }

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return webClient.get().uri("/user?userId={username}", username)
      .retrieve()
      .bodyToMono(UserDTO.class)
      .onErrorReturn(new UserDTO())
      .map(mapper::convert);
  }
}
