package io.sparktype.purchase.repository;

import io.sparktype.commons.jpa.TimeAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale extends TimeAware {

  private Long userId;

  private Long price;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "sale_id")
  private Collection<SaleItem> saleItems;

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
