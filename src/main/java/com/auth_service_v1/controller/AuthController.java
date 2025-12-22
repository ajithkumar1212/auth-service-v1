package com.auth_service_v1.controller;

import com.auth_service_v1.dto.*;
import com.auth_service_v1.service.IJwtService;
import com.auth_service_v1.service.IOtpService;
import com.auth_service_v1.service.IUserService;
import jakarta.validation.Valid;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final IOtpService otpService;
  private final IUserService userService;
  private final IJwtService jwtService;

  @PostMapping("/send-otp")
  public ResponseEntity<?> sendOtp(@Valid @RequestBody SignupRequest request) {
    String otp = otpService.generateOtp(request.getPhoneNumber());
    return ResponseEntity.ok("OTP sent: " + otp); // In production, send via SMS
  }

  @PostMapping("/verify-otp")
  public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
    if (otpService.verifyOtp(request.getPhoneNumber(), request.getOtp())) {
      UserDto userDto = userService.createOrVerifyUser(request.getPhoneNumber());
      String accessToken = jwtService.generateAccessToken(userDto);
      String refreshToken = jwtService.generateRefreshToken(userDto);
      Date accessExpiry = jwtService.getExpirationDateFromToken(accessToken);
      Date refreshExpiry = jwtService.getExpirationDateFromToken(refreshToken);
      return ResponseEntity.ok(
          new AuthResponse(accessToken, refreshToken, accessExpiry, refreshExpiry));
    } else {
      return ResponseEntity.badRequest().body("Invalid OTP");
    }
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
    String refreshToken = request.getRefreshToken();
    if (jwtService.validateToken(refreshToken)) {
      String phone = jwtService.getUsernameFromToken(refreshToken);
      UserDto userDto = userService.findByPhoneNumber(phone);
      String newAccessToken = jwtService.generateAccessToken(userDto);
      Date newAccessExpiry = jwtService.getExpirationDateFromToken(newAccessToken);
      Date refreshExpiry = jwtService.getExpirationDateFromToken(refreshToken);
      return ResponseEntity.ok(
          new AuthResponse(newAccessToken, refreshToken, newAccessExpiry, refreshExpiry));
    } else {
      return ResponseEntity.badRequest().body("Invalid refresh token");
    }
  }

  @GetMapping("/user/profile")
  public String userProfile() {
    return "User profile accessed";
  }

  @GetMapping("/admin/dashboard")
  public String adminDashboard() {
    return "Admin dashboard accessed";
  }
}
