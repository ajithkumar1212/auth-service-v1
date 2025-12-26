package com.auth_service_v1.entity.user;

import com.auth_service_v1.entity.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends AuditableEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  public Permission(String name) {
    this.name = name;
  }
}
