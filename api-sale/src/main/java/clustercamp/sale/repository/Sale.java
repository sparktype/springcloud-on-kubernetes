package clustercamp.sale.repository;

import clustercamp.base.dto.SaleDTO;
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
public class Sale extends BaseEntity {

  private Long userId;

  private Long price;

  public static Sale of(SaleDTO dto) {
    var order = new Sale();
    BeanUtils.copyProperties(dto, order);
    return order;
  }

  public SaleDTO to() {
    var dto = new SaleDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public Sale from(SaleDTO dto) {
    BeanUtils.copyProperties(dto, this, "id");
    return this;
  }
}
