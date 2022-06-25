package io.sparktype.sales.repository;

import io.sparktype.commons.jpa.TimeAware;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SaleItem extends TimeAware {

  private Long goodId;

  private Integer count;

  private Long price;

  public static SaleItem of(Long id) {
    var saleItem = new SaleItem();
    saleItem.setId(id);
    return saleItem;
  }

  public SaleItem from(SaleItem request) {
    count = request.getCount();
    return this;
  }
}
