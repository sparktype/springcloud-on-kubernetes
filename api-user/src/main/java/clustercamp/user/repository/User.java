package clustercamp.user.repository;

import clustercamp.base.dto.UserDTO;
import clustercamp.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

  private String userName;

  private String realName;

  private String userPass;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;

  public static User of(UserDTO userDTO) {
    var user = new User();
    BeanUtils.copyProperties(userDTO, user);
    return user;
  }

  public UserDTO to() {
    var dto = new UserDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public User from(UserDTO userDTO) {
    BeanUtils.copyProperties(userDTO, this, "id");
    return this;
  }

}
