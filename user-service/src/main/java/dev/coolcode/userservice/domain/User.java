package dev.coolcode.userservice.domain;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

  private Long id;

  private String userId;

  private String userName;

  private String password;

  private String email;

}
