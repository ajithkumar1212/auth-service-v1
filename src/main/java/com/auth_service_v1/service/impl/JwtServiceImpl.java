package com.auth_service_v1.service.impl;

import com.auth_service_v1.config.JwtConfig;
import com.auth_service_v1.dto.UserDto;
import com.auth_service_v1.service.IJwtService;
import com.auth_service_v1.utils.JwtUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements IJwtService {

  private final JwtConfig jwtConfig;

  public JwtServiceImpl(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  public String generateAccessToken(UserDto user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", user.getRoles().stream().map(r -> r.getName()).toList());
    return JwtUtils.generateToken(
        jwtConfig.getSecret(), claims, user.getPhoneNumber(), jwtConfig.getExpiration());
  }

  public String generateRefreshToken(UserDto user) {
    return JwtUtils.generateToken(
        jwtConfig.getSecret(),
        new HashMap<>(),
        user.getPhoneNumber(),
        jwtConfig.getRefreshExpiration());
  }

  public String getUsernameFromToken(String token) {
    return JwtUtils.getUsernameFromToken(jwtConfig.getSecret(), token);
  }

  public boolean validateToken(String token) {
    return JwtUtils.validateToken(jwtConfig.getSecret(), token);
  }

  public Date getExpirationDateFromToken(String token) {
    return JwtUtils.getExpirationDateFromToken(jwtConfig.getSecret(), token);
  }
}
