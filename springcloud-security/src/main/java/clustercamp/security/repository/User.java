package clustercamp.security.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import clustercamp.security.service.BaseUserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  private Long id;

  private String username;

  private String password;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;


  public UserDetails to() {
    var details = new BaseUserDetails();
    BeanUtils.copyProperties(this, details);
    details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    return details;
  }
}
