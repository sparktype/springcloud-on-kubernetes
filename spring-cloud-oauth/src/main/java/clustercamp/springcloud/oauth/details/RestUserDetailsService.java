package clustercamp.springcloud.oauth.details;

import clustercamp.springcloud.base.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestUserDetailsService implements UserDetailsService {

  @Autowired
  private RestTemplate restTemplate;


  @Override
  public UserDetails loadUserByUsername(String username) {
    UriComponents uriComponents = UriComponentsBuilder
      .fromUriString("http://USER-SERVICE/api/v1/auth/user/{username}")
      .buildAndExpand(username)
      .encode();

    HttpEntity<UserDto> entity = restTemplate.getForEntity(uriComponents.toUri(), UserDto.class);

    if (!entity.hasBody()) {
      throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    }

    BaseUserDetails userDetails = new BaseUserDetails();

    UserDto user = entity.getBody();

    userDetails.setUsername(username);
    userDetails.setPassword(user.getUserPass());
    userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));

    userDetails.setAccountNonExpired(user.getAccountNonExpired().booleanValue());
    userDetails.setAccountNonLocked(user.getAccountNonLocked().booleanValue());
    userDetails.setCredentialsNonExpired(user.getCredentialsNonExpired().booleanValue());
    userDetails.setEnabled(user.getEnabled().booleanValue());

    return userDetails;
  }
}
