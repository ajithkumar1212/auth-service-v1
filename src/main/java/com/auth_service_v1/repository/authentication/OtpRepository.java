package com.auth_service_v1.repository.authentication;

import com.auth_service_v1.entity.authentication.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OtpEntity, String> {}
