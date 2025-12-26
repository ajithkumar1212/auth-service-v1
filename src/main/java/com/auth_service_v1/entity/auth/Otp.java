package com.auth_service_v1.entity.auth;

import com.auth_service_v1.entity.audit.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_otp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Otp extends AuditableEntity {

  private static final long serialVersionUID = 1L;

  @Id private String mobileNumber;
  private String otp;
  private LocalDateTime expiry;
}
