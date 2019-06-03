package clustercamp.user.repository;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import clustercamp.base.jpa.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

  private String username;

  private String password;

  private String realname;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;

  public static User of(Long id) {
    var user = new User();
    user.setId(id);
    return user;
  }

  public User from(User user) {
    realname = user.getRealname();
    password = user.getPassword();
    enabled = user.getEnabled();
    return this;
  }
}
