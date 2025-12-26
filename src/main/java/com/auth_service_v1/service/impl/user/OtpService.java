package com.auth_service_v1.service.impl.user;

import com.auth_service_v1.entity.auth.OtpEntity;
import com.auth_service_v1.repository.auth.OtpRepository;
import com.auth_service_v1.service.user.IOtpService;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService implements IOtpService {

  @Autowired private OtpRepository otpRepository;

  public String generateOtp(String mobileNumber) {
    String otp = String.valueOf(100000 + new Random().nextInt(900000));
    OtpEntity otpEntity = otpRepository.findById(mobileNumber).orElse(null);
    if (otpEntity != null) {
      otpEntity.setOtp(otp);
      otpEntity.setExpiry(LocalDateTime.now().plusMinutes(5));
    } else {
      otpEntity = new OtpEntity(mobileNumber, otp, LocalDateTime.now().plusMinutes(5));
    }
    otpRepository.save(otpEntity);
    return otp;
  }

  public boolean verifyOtp(String mobileNumber, String otp) {
    OtpEntity otpEntity = otpRepository.findById(mobileNumber).orElse(null);
    if (otpEntity != null
        && otpEntity.getOtp().equals(otp)
        && otpEntity.getExpiry().isAfter(LocalDateTime.now())) {
      otpRepository.delete(otpEntity);
      return true;
    }
    return false;
  }
}
