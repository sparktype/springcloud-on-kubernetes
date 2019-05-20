package clustercamp.user.domain;

import clustercamp.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends BaseEntity {

  @Id
  private long id;

  private String userId;

  private String realName;

  private String userPass;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;

}
