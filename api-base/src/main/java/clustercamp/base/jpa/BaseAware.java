package clustercamp.base.jpa;

import java.time.LocalDateTime;

public interface BaseAware {

  Long getId();

  String getCreateBy();

  LocalDateTime getCreateAt();

  String getModifyBy();

  LocalDateTime getModifyAt();
}
