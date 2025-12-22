package com.auth_service_v1.dto;

import java.util.Set;
import lombok.Data;

@Data
public class RoleDto {

  private Long id;
  private String name;
  private Set<PermissionDto> permissions;
}
