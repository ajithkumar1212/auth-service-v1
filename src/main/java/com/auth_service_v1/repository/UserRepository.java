package com.auth_service_v1.repository;

import com.auth_service_v1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByPhoneNumber(String phoneNumber);
}
