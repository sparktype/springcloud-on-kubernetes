package clustercamp.ship.repository;

import io.sparktype.commons.jpa.TimeAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ship extends TimeAware {

  private Long saleId;

  private String status;

  public static Ship of(Long id) {
    var ship = new Ship();
    ship.setId(id);
    return ship;
  }

  public Ship from(Ship ship) {
    status = ship.getStatus();
    return this;
  }
}
