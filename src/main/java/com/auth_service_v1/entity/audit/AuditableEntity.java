package com.auth_service_v1.entity.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable {

  @NotNull
  @CreatedBy
  @Size(max = 250)
  @Column(name = "CREATED_BY", updatable = false, length = 250)
  private String createdBy = "IP";

  @NotNull
  @CreatedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE_TIME", updatable = false, columnDefinition = "TIMESTAMP(3)")
  private Timestamp createdDateTime;

  @LastModifiedBy
  @Size(max = 250)
  @Column(name = "UPDATED_BY", length = 250)
  private String updatedBy;

  @LastModifiedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "UPDATED_DATE_TIME", columnDefinition = "TIMESTAMP(3)")
  private Timestamp updatedDateTime;
}
