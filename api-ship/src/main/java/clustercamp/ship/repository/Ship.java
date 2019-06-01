package clustercamp.ship.repository;

import clustercamp.base.dto.ShipDTO;
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
public class Ship extends BaseEntity {

  private Long saleId;

  private String status;

  public static Ship of(ShipDTO dto) {
    var domain = new Ship();
    BeanUtils.copyProperties(dto, domain);
    return domain;
  }

  public ShipDTO to() {
    var dto = new ShipDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public Ship from(ShipDTO dto) {
    BeanUtils.copyProperties(dto, this, "id");
    return this;
  }
}
