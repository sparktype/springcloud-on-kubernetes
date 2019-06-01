package clustercamp.zuul.config;

//@Configuration
//@EnableOAuth2Sso
//@Order(value = 0)
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private final ResourceServerTokenServices resourceServerTokenServices;
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//      .authorizeRequests()
//      .antMatchers("/authorization-server-1/**",
//        "/login").permitAll()
//      .anyRequest().authenticated().and()
//      .exceptionHandling().authenticationEntryPoint(
//      (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//      .and()
//      .logout().permitAll().logoutSuccessUrl("/");
//  }
//}
