package clustercamp.base.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements CreateModifyAware, IdentityAware {

  @Id
  private Long id;

  @CreatedDate
  @Column(name = "create_at", updatable = false)
  private LocalDateTime createAt;

  @Column(name = "create_by", nullable = false)
  @CreatedBy
  private String createBy;


  @LastModifiedDate
  @Column(name = "modify_at")
  private LocalDateTime modifyAt;

  @LastModifiedBy
  @Column(name = "modify_by")
  private String modifyBy;

}
