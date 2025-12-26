package com.auth_service_v1.controller;

import com.auth_service_v1.dto.auth.AuthResponse;
import com.auth_service_v1.dto.auth.SignupRequest;
import com.auth_service_v1.dto.auth.VerifyOtpRequest;
import com.auth_service_v1.security.token.OtpAuthenticationToken;
import com.auth_service_v1.service.auth.IJwtService;
import com.auth_service_v1.service.user.IOtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final IOtpService otpService;
  private final IJwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/request-otp")
  public ResponseEntity<?> sendOtp(@Valid @RequestBody SignupRequest request) {
    String otp = otpService.generateOtp(request.getMobileNumber());
    return ResponseEntity.ok("OTP sent: " + otp); // In production, send via SMS
  }

  @PostMapping("/verify-otp")
  public ResponseEntity<AuthResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new OtpAuthenticationToken(request.getMobileNumber(), request.getOtp()));
    return ResponseEntity.ok(jwtService.generateAuthorizationToken(authentication));
  }
}
