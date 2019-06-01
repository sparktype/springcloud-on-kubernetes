package clustercamp.base.dto;

import clustercamp.base.jpa.BaseAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SaleItemDTO implements BaseAware {

  private Long id;

  private GoodDTO good;

  private Integer count;

  private Long price;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;

}