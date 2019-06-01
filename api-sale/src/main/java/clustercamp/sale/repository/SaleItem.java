package clustercamp.sale.repository;

import clustercamp.base.dto.SaleItemDTO;
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
public class SaleItem extends BaseEntity {

  private Long goodId;

  private Integer count;

  private Long price;

  public static SaleItem of(SaleItemDTO dto) {
    var domain = new SaleItem();
    BeanUtils.copyProperties(dto, domain);
    return domain;
  }

  public SaleItemDTO to() {
    var dto = new SaleItemDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public SaleItem from(SaleItemDTO dto) {
    BeanUtils.copyProperties(dto, this, "id");
    return this;
  }
}
