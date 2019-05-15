package clustercamp.springcloud.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {

  @Id
  private Long id;

  private String userId;

  private String userName;

  private String password;

  private String email;

}
