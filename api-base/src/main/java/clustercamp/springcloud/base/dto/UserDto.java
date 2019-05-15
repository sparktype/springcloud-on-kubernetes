package clustercamp.springcloud.base.dto;

import clustercamp.springcloud.base.jpa.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

  private long id;

  private String userId;

  private String realName;

  private String userPass;

  private String roles;

  private YesNo accountNonExpired;

  private YesNo accountNonLocked;

  private YesNo credentialsNonExpired;

  private YesNo enabled;
}
