package com.auth_service_v1.repository.user;

import com.auth_service_v1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByMobileNumber(String mobileNumber);
}
