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
@Schema(description = "Verify OTP request")
public class VerifyOtpRequest {

  @JsonProperty("mobileNumber")
  @NotNull
  @Schema(description = "The mobile number", example = "9988776655")
  private String mobileNumber;

  @JsonProperty("otp")
  @NotNull
  @Schema(description = "The OTP code", example = "123456")
  private String otp;
}
