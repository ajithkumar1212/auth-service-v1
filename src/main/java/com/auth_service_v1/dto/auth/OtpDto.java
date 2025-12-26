package com.auth_service_v1.dto.auth;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {

  private String mobileNumber;
  private String otp;
  private LocalDateTime expiry;
}
