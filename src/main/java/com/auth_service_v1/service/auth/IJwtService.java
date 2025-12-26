package com.auth_service_v1.service.auth;

import com.auth_service_v1.dto.auth.AuthResponse;
import com.auth_service_v1.dto.user.UserDto;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public interface IJwtService {

  public AuthResponse generateAuthorizationToken(Authentication auth);

  public Collection<GrantedAuthority> getAuthorities(String token);

  public String getUsernameFromToken(String token);

  String generateRefreshToken(UserDto user);

  public boolean isValidateToken(String token);

  Date getExpirationDateFromToken(String token);
}
