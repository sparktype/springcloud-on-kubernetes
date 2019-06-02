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

  private Long saleId;

  private String status;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;


  public static ShipDTO of(Long id) {
    var dto = new ShipDTO();
    dto.setId(id);
    return dto;
  }
}
