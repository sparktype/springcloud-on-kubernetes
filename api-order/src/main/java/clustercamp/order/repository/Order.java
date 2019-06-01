package clustercamp.order.repository;

import clustercamp.base.dto.OrderDTO;
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
public class Order extends BaseEntity {

  private Long userId;

  private Long price;

  public static Order of(OrderDTO dto) {
    var order = new Order();
    BeanUtils.copyProperties(dto, order);
    return order;
  }

  public OrderDTO to() {
    var dto = new OrderDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public Order from(OrderDTO dto) {
    BeanUtils.copyProperties(dto, this, "id");
    return this;
  }
}
