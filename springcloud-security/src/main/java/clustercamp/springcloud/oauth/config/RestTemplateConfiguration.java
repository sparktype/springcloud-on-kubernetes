package clustercamp.springcloud.oauth.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  @LoadBalanced
  @Bean
  public RestTemplate loadbalancedRestTemplate() {
    return new RestTemplate();
  }

}
