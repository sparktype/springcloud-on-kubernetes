package io.sparktype.stock.repository;

import io.sparktype.commons.jpa.TimeAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stock extends TimeAware {

  private String name;

  private String description;

  private Long price;

  public static Stock of(Long id) {
    var good = new Stock();
    good.setId(id);
    return good;
  }

  public Stock from(Stock request) {
    name = request.getName();
    description = request.getDescription();
    price = request.getPrice();
    return this;
  }
}
