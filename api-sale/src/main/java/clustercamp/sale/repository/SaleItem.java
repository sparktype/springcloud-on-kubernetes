package clustercamp.sale.repository;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import clustercamp.base.jpa.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SaleItem extends BaseEntity {

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
