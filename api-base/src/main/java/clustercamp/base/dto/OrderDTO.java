package clustercamp.base.dto;

import clustercamp.base.jpa.BaseAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements BaseAware {

  private Long id;

  private Long userId;

  private Long price;

  private Collection<OrderDetailDTO> orderDetails;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;
}
