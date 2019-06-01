package clustercamp.order.repository;

import clustercamp.base.dto.GoodDTO;
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

  private Long price;

  public static Order of(GoodDTO goodDTO) {
    var good = new Order();
    BeanUtils.copyProperties(goodDTO, good);
    return good;
  }

  public GoodDTO to() {
    var dto = new GoodDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public Order from(GoodDTO goodDTO) {
    BeanUtils.copyProperties(goodDTO, this, "id");
    return this;
  }
}
