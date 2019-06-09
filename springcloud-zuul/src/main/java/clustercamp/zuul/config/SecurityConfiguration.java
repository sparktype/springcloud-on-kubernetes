package clustercamp.zuul.config;

//@Configuration
//@EnableResourceServer
//@Order(value = 0)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//  private static final String[] PATHS = {
//    "/v2/api-docs",
//    "/swagger-resources/**",
//    "/configuration/ui",
//    "/configuration/security",
//    "/swagger-ui.html",
//    "/webjars/**"
//  };
//  @Autowired
//  private ResourceServerTokenServices resourceServerTokenServices;
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//      .authorizeRequests()
//      .antMatchers(PATHS).permitAll()
//      .antMatchers("/actuator/**", "/login").permitAll()
//      .anyRequest().authenticated();
//  }
//
//  @Bean
//  @Primary
//  public OAuth2ClientContextFilter oauth2ClientContextFilterWithPath() {
//    return new OAuth2ClientFilter();
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//      .authorizeRequests()
//      .antMatchers("/oauth/**", "/actuator/**").permitAll()
//      .anyRequest().authenticated().and()
//      .exceptionHandling()
//      .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//      .and().addFilterAfter(oAuth2AuthenticationProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class)
//      .logout().permitAll().logoutSuccessUrl("/");
//  }
//
//  private OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter() {
//    OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter = new OAuth2AuthenticationProcessingFilter();
//    oAuth2AuthenticationProcessingFilter.setAuthenticationManager(oauthAuthenticationManager());
//    oAuth2AuthenticationProcessingFilter.setStateless(false);
//
//    return oAuth2AuthenticationProcessingFilter;
//  }
//
//  private AuthenticationManager oauthAuthenticationManager() {
//    OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
//    oAuth2AuthenticationManager.setResourceId("API");
//    oAuth2AuthenticationManager.setTokenServices(resourceServerTokenServices);
//    oAuth2AuthenticationManager.setClientDetailsService(null);
//
//    return oAuth2AuthenticationManager;
//  }
//}
