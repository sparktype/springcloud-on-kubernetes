package clustercamp.security.service;

//@Component
//public class BaseUserDetailsService implements ReactiveUserDetailsService {
//
//  private final WebClient webClient;
//
//  public BaseUserDetailsService(WebClient.Builder builder) {
//    webClient = builder.baseUrl("http://API-USER-SERVICE")
//      .defaultHeader(HttpHeaders.USER_AGENT, "ClusterCampOAuth2.0")
//      .build();
//  }
//
//  @Override
//  public Mono<UserDetails> findByUsername(String username) {
////    return webClient.get().uri("/user?userId={username}", username)
////      .retrieve()
////      .bodyToMono(UserDTO.class);
////      .
//    return null;
//  }
//}
