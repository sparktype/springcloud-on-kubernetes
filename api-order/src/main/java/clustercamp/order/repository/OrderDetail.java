package clustercamp.order.repository;

import clustercamp.base.dto.OrderDetailDTO;
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
public class OrderDetail extends BaseEntity {

  private Long goodId;

  private Integer count;

  private Long price;

  public static OrderDetail of(OrderDetailDTO dto) {
    var domain = new OrderDetail();
    BeanUtils.copyProperties(dto, domain);
    return domain;
  }

  public OrderDetailDTO to() {
    var dto = new OrderDetailDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public OrderDetail from(OrderDetailDTO dto) {
    BeanUtils.copyProperties(dto, this, "id");
    return this;
  }
}
