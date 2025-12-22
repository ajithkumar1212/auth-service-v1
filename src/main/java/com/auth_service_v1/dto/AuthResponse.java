package com.auth_service_v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Schema(description = "Authentication response")
public class AuthResponse {

  @JsonProperty("accessToken")
  @NotNull
  @Schema(description = "Access token")
  private String accessToken;

  @JsonProperty("refreshToken")
  @NotNull
  @Schema(description = "Refresh token")
  private String refreshToken;

  @JsonProperty("accessTokenExpiry")
  @NotNull
  @Schema(description = "Access token expiry date")
  private Date accessTokenExpiry;

  @JsonProperty("refreshTokenExpiry")
  @NotNull
  @Schema(description = "Refresh token expiry date")
  private Date refreshTokenExpiry;

  public AuthResponse(
      String accessToken, String refreshToken, Date accessTokenExpiry, Date refreshTokenExpiry) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.accessTokenExpiry = accessTokenExpiry;
    this.refreshTokenExpiry = refreshTokenExpiry;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Date getAccessTokenExpiry() {
    return accessTokenExpiry;
  }

  public void setAccessTokenExpiry(Date accessTokenExpiry) {
    this.accessTokenExpiry = accessTokenExpiry;
  }

  public Date getRefreshTokenExpiry() {
    return refreshTokenExpiry;
  }

  public void setRefreshTokenExpiry(Date refreshTokenExpiry) {
    this.refreshTokenExpiry = refreshTokenExpiry;
  }
}
