package clustercamp.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO extends BaseDTO implements CreateAware, ModifyAware {

  private long id;

  private String userId;

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
}
