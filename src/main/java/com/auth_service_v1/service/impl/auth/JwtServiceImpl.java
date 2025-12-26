package com.auth_service_v1.service.impl.auth;

import com.auth_service_v1.config.JwtConfig;
import com.auth_service_v1.dto.auth.AuthResponse;
import com.auth_service_v1.dto.user.UserDto;
import com.auth_service_v1.service.auth.IJwtService;
import com.auth_service_v1.utils.JwtUtils;
import java.util.Collection;
import java.util.Date;
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
    String accessToken =
        JwtUtils.generateAccessToken(auth, jwtConfig.getSecret(), jwtConfig.getExpiration());
    String refreshToken =
        JwtUtils.generateAccessToken(auth, jwtConfig.getSecret(), jwtConfig.getRefreshExpiration());
    return new AuthResponse(
        accessToken, refreshToken, jwtConfig.getExpiration(), jwtConfig.getRefreshExpiration());
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities(String token) {
    return JwtUtils.getAuthorities(token, jwtConfig.getSecret());
  }

  @Override
  public String getUsernameFromToken(String token) {
    return JwtUtils.getUsernameFromToken(token, jwtConfig.getSecret());
  }

  @Override
  public boolean isValidateToken(String token) {
    return JwtUtils.isvalidate(token, jwtConfig.getSecret());
  }

  public Date getExpirationDateFromToken(String token) {
    return JwtUtils.getExpirationDateFromToken(jwtConfig.getSecret(), token);
  }

  @Override
  public String generateRefreshToken(UserDto user) {
    throw new UnsupportedOperationException("Unimplemented method 'generateRefreshToken'");
  }
}
