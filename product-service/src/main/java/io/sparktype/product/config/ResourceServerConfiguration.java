package io.sparktype.product.config;

import io.sparktype.commons.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(Swagger.PATHS).permitAll()
        .antMatchers("/", "/actuator/**", "/csrf", "/version").permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
