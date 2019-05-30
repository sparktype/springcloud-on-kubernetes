package clustercamp.ship.repository;

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
public class Ship extends BaseEntity {

  private String name;

  private String description;

  private Long price;

  public static Ship of(GoodDTO goodDTO) {
    var good = new Ship();
    BeanUtils.copyProperties(goodDTO, good);
    return good;
  }

  public GoodDTO to() {
    var dto = new GoodDTO();
    BeanUtils.copyProperties(this, dto);
    return dto;
  }

  public Good from(GoodDTO goodDTO) {
    BeanUtils.copyProperties(goodDTO, this, "id");
    return this;
  }
}
