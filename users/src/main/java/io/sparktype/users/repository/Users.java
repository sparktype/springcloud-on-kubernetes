package io.sparktype.users.repository;

import io.sparktype.commons.jpa.TimeAware;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users extends TimeAware {

  private String username;

  private String password;

  private String realname;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;

  public static Users of(Long id) {
    var user = new Users();
    user.setId(id);
    return user;
  }

  public Users from(Users users) {
    realname = users.getRealname();
    password = users.getPassword();
    enabled = users.getEnabled();
    return this;
  }
}
