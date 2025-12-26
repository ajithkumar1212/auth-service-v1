package com.auth_service_v1.repository.auth;

import com.auth_service_v1.entity.auth.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {}
