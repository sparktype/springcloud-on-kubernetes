package clustercamp.base.jpa;

import java.time.LocalDateTime;

public interface CreateModifyAware {

  String getCreateBy();

  LocalDateTime getCreateAt();

  String getModifyBy();

  LocalDateTime getModifyAt();
}
