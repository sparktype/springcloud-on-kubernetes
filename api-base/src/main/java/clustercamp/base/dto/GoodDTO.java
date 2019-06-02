package clustercamp.base.dto;

import clustercamp.base.jpa.BaseAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GoodDTO implements BaseAware {

  private Long id;

  private String name;

  private String description;

  private Long price;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;

  public static GoodDTO of(Long id) {
    var dto = new GoodDTO();
    dto.setId(id);
    return dto;
  }
}
