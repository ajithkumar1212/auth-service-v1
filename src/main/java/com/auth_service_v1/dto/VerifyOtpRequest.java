package com.auth_service_v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Verify OTP request")
public class VerifyOtpRequest {

  @JsonProperty("phoneNumber")
  @NotNull
  @Schema(description = "The phone number", example = "1234567890")
  private String phoneNumber;

  @JsonProperty("otp")
  @NotNull
  @Schema(description = "The OTP code", example = "123456")
  private String otp;

  public VerifyOtpRequest() {}

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }
}
