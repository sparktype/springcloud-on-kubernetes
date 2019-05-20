package clustercamp.base.dto;

import java.time.LocalDateTime;

public interface CreateAware {
  String getCreateBy();

  LocalDateTime getCreateAt();
}
