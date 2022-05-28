package clustercamp.good.repository;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.sparktype.commons.jpa.TimeAware;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Good extends TimeAware {

  private String name;

  private String description;

  private Long price;

  public static Good of(Long id) {
    var good = new Good();
    good.setId(id);
    return good;
  }

  public Good from(Good request) {
    name = request.getName();
    description = request.getDescription();
    price = request.getPrice();
    return this;
  }
}
