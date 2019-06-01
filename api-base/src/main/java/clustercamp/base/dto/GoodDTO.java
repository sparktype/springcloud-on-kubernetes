package clustercamp.base.dto;

import clustercamp.base.jpa.CreateModifyAware;
import clustercamp.base.jpa.IdentityAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GoodDTO implements IdentityAware, CreateModifyAware {

  private Long id;

  private String name;

  private String description;

  private Long price;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;
}
