package com.auth_service_v1.repository;

import com.auth_service_v1.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {}
