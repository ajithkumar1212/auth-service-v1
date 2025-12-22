package com.auth_service_v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OtpDto {

  private String phoneNumber;
  private String otp;
  private LocalDateTime expiry;
}
