package com.auth_service_v1.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
  private long accessTokenExpiry;

  @JsonProperty("refreshTokenExpiry")
  @NotNull
  @Schema(description = "Refresh token expiry date")
  private long refreshTokenExpiry;
}
