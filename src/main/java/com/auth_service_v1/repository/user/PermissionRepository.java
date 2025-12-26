package com.auth_service_v1.repository.user;

import com.auth_service_v1.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}
