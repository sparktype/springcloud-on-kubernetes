package clustercamp.zuul.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableOAuth2Sso
@Order(value = 0)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final ResourceServerTokenServices resourceServerTokenServices;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/oauth/**", "/login").permitAll()
      .anyRequest().authenticated().and()
      .exceptionHandling().authenticationEntryPoint(
      (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
      .and()
      .logout().permitAll().logoutSuccessUrl("/");
  }
}
