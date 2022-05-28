package io.sparktype.commons.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeAware implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "create_at", updatable = false)
  private LocalDateTime createAt;

  @LastModifiedDate
  @Column(name = "modify_at")
  private LocalDateTime modifyAt;

}
