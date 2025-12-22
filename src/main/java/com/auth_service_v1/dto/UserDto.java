package com.auth_service_v1.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UserDto {

  private Long id;
  private String phoneNumber;
  private Set<RoleDto> roles;
}
