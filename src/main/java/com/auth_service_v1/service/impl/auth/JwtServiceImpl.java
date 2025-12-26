package com.auth_service_v1.service.impl.auth;

import com.auth_service_v1.config.JwtConfig;
import com.auth_service_v1.dto.auth.AuthResponse;
import com.auth_service_v1.dto.user.UserDto;
import com.auth_service_v1.service.auth.IJwtService;
import com.auth_service_v1.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements IJwtService {

  private final JwtConfig jwtConfig;

  public JwtServiceImpl(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Override
  public AuthResponse generateAuthorizationToken(Authentication auth) {
    UUID refreshUUIDId = UUID.randomUUID();
    String accessToken =
        JwtUtils.generateAccessToken(
            auth, jwtConfig.getAccessSecret(), jwtConfig.getAccessExpiration());

    return new AuthResponse(
        accessToken,
        accessToken,
        jwtConfig.getAccessExpiration(),
        jwtConfig.getRefreshExpiration());
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities(String token) {
    return JwtUtils.getAuthorities(token, jwtConfig.getAccessSecret());
  }

  @Override
  public String getUsernameFromToken(String token) {
    return JwtUtils.getUsernameFromToken(token, jwtConfig.getAccessSecret());
  }

  @Override
  public boolean isValidateToken(String token) {
    return JwtUtils.isvalidate(token, jwtConfig.getAccessSecret());
  }

  public Date getExpirationDateFromToken(String token) {
    return JwtUtils.getExpirationDateFromToken(jwtConfig.getAccessSecret(), token);
  }

  @Override
  public String generateRefreshToken(UserDto user) {
    throw new UnsupportedOperationException("Unimplemented method 'generateRefreshToken'");
  }

  @Override
  public Claims getAllClaims(String token) {
    return JwtUtils.getAllClaims(token, jwtConfig.getAccessSecret());
  }
}
