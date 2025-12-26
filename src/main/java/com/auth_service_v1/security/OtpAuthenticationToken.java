package com.auth_service_v1.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class OtpAuthenticationToken extends AbstractAuthenticationToken {

  private final String mobile;
  private final String otp;

  public OtpAuthenticationToken(String mobile, String otp) {
    super(null);
    this.mobile = mobile;
    this.otp = otp;
    setAuthenticated(false);
  }

  public OtpAuthenticationToken(UserDetails user) {
    super(user.getAuthorities());
    this.mobile = user.getUsername();
    this.otp = null;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return otp;
  }

  @Override
  public Object getPrincipal() {
    return mobile;
  }
}
