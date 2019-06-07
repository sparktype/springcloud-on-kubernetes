package clustercamp.sale.repository;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import clustercamp.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale extends BaseEntity {

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
