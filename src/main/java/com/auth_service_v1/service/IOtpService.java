package com.auth_service_v1.service;

public interface IOtpService {

  String generateOtp(String phoneNumber);

  boolean verifyOtp(String phoneNumber, String otp);
}
