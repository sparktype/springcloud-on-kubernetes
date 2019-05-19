package clustercamp.springcloud.user.domain;

import clustercamp.springcloud.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

  @Id
  private Long id;

  private String userId;

  private String userName;

  private String password;

  private String email;

}
