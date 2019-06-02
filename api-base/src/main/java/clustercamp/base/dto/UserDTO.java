package clustercamp.base.dto;

import clustercamp.base.jpa.BaseAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements BaseAware {

  private Long id;

  private String userName;

  private String realName;

  private String userPass;

  private String roles;

  private Boolean accountNonExpired;

  private Boolean accountNonLocked;

  private Boolean credentialsNonExpired;

  private Boolean enabled;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;


  public static UserDTO of(Long id) {
    var dto = new UserDTO();
    dto.setId(id);
    return dto;
  }

  public static UserDTO of(String userName) {
    var dto = new UserDTO();
    dto.setUserName(userName);
    return dto;
  }
}
