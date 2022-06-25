package io.sparktype.product.repository;

import io.sparktype.commons.jpa.TimeAware;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends TimeAware {

  private String name;

  private String description;

  private Long price;

  public static Product of(Long id) {
    var good = new Product();
    good.setId(id);
    return good;
  }

  public Product from(Product request) {
    name = request.getName();
    description = request.getDescription();
    price = request.getPrice();
    return this;
  }
}
