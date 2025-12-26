package com.auth_service_v1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  @PreAuthorize("hasAuthority('USER_READ')")
  @GetMapping("/profile")
  public String userProfile() {
    return "User profile accessed";
  }

  @PreAuthorize("hasAuthority('ROLE_MANAGE')")
  @GetMapping("/dashboard")
  public String adminDashboard() {
    return "Admin dashboard accessed";
  }
}
