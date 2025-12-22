package com.auth_service_v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Signup request")
public class SignupRequest {

  @JsonProperty("phoneNumber")
  @NotNull
  @Schema(description = "The phone number for signup", example = "1234567890")
  private String phoneNumber;

  public SignupRequest() {}

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
