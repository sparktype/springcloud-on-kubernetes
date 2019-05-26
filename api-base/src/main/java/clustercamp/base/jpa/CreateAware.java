package clustercamp.base.jpa;

import java.time.LocalDateTime;

public interface CreateAware {
  String getCreateBy();
  LocalDateTime getCreateAt();
}
