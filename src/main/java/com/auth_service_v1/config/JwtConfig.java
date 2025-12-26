package com.auth_service_v1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
  private String accessSecret;
  private String refreshSecret;
  private long accessExpiration;
  private long refreshExpiration;
}
