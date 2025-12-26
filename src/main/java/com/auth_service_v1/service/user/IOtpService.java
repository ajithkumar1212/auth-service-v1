package com.auth_service_v1.service.user;

public interface IOtpService {

  String generateOtp(String mobileNumber);

  boolean verifyOtp(String mobileNumber, String otp);
}
