package io.sparktype.users.config;

import io.sparktype.commons.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
  private static final String[] SERVICE_PATH = {
      "/", "/actuator/**", "/product/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
        .formLogin().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests((request) -> request.antMatchers(Swagger.PATHS).permitAll()
            .antMatchers(SERVICE_PATH).permitAll())
        .httpBasic(Customizer.withDefaults())
        .build();
  }
}
