package clustercamp.sale.repository;

import clustercamp.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale extends BaseEntity {

  private Long userId;

  private Long price;

  public static Sale of(Long id) {
    var sale = new Sale();
    sale.setId(id);
    return sale;
  }

  public Sale from(Sale request) {
    price = request.getPrice();
    return this;
  }
}
