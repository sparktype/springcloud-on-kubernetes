package io.sparktype.product.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

  @Id
  private Long id;
  private String name;

  private String description;

  private Long price;

  public static Product of(Long id) {
    var product = new Product();
    product.setId(id);
    return product;
  }

  public Product from(Product request) {
    name = request.getName();
    description = request.getDescription();
    price = request.getPrice();
    return this;
  }
}
