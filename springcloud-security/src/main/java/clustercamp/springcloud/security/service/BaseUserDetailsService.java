package clustercamp.springcloud.security.service;

import clustercamp.springcloud.base.dto.ClientDto;
import clustercamp.springcloud.security.details.BaseUserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BaseUserDetailsService implements ReactiveUserDetailsService {

  private final WebClient webClient;

  public BaseUserDetailsService(WebClient.Builder builder) {
    webClient = builder.baseUrl("http://API-USER-SERVICE")
      .defaultHeader(HttpHeaders.USER_AGENT, "ClusterCampOAuth2.0")
      .build();
  }

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return webClient.get().uri("/client?clientID={clientId}", username)
      .retrieve()
      .bodyToMono(ClientDto.class)
      .onErrorReturn(new ClientDto())
      .map(u -> {
        var details = new BaseUserDetails();

//        details (username);
//        details.setPassword(user.getUserPass());
//        details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
//
//        details.setAccountNonExpired(user.getAccountNonExpired().booleanValue());
//        details.setAccountNonLocked(user.getAccountNonLocked().booleanValue());
//        details.setCredentialsNonExpired(user.getCredentialsNonExpired().booleanValue());
//        details.setEnabled(user.getEnabled().booleanValue());
        return details;
      });
  }
}
