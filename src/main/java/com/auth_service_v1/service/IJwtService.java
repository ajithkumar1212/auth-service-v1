package com.auth_service_v1.service;

import com.auth_service_v1.dto.UserDto;
import java.util.Date;

public interface IJwtService {

  String generateAccessToken(UserDto user);

  String generateRefreshToken(UserDto user);

  String getUsernameFromToken(String token);

  boolean validateToken(String token);

  Date getExpirationDateFromToken(String token);
}
