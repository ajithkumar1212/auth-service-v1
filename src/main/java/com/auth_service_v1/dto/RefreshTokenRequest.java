package com.auth_service_v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Refresh token request")
public class RefreshTokenRequest {

  @JsonProperty("refreshToken")
  @NotNull
  @Schema(description = "Refresh token")
  private String refreshToken;

  public RefreshTokenRequest() {}

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
