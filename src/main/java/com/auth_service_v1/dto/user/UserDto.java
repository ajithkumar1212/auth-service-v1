package com.auth_service_v1.dto.user;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String mobileNumber;
  private Set<RoleDto> roles;
}
