package clustercamp.springcloud.zuul.config;

import clustercamp.springcloud.zuul.filter.LoggingPostFilter;
import clustercamp.springcloud.zuul.filter.LoggingPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

  @Bean
  public LoggingPostFilter loggingPostFilter() {
    return new LoggingPostFilter();
  }

  @Bean
  public LoggingPreFilter loggingPreFilter() {
    return new LoggingPreFilter();
  }

}
