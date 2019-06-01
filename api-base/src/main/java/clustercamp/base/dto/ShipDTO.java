package clustercamp.base.dto;


import clustercamp.base.jpa.BaseAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShipDTO implements BaseAware {

  private Long id;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;

}
