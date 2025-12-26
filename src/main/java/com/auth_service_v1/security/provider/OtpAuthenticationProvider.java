package com.auth_service_v1.security.provider;

import com.auth_service_v1.dto.user.UserDto;
import com.auth_service_v1.security.token.OtpAuthenticationToken;
import com.auth_service_v1.service.impl.user.OtpService;
import com.auth_service_v1.service.impl.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("otpAuthenticationProvider")
public class OtpAuthenticationProvider implements AuthenticationProvider {

  private final OtpService otpService;
  private final UserService userService;

  public OtpAuthenticationProvider(OtpService otpService, UserService userService) {
    this.otpService = otpService;
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication auth) {

    if (!(auth instanceof OtpAuthenticationToken)) {
      return null;
    }

    String mobileNumber = auth.getPrincipal().toString();

    Object credentials = auth.getCredentials();
    if (credentials == null) {
      throw new BadCredentialsException("OTP is missing");
    }

    String otp = credentials.toString();

    // 1. Verify OTP
    if (!otpService.verifyOtp(mobileNumber, otp)) {
      throw new BadCredentialsException("Invalid OTP");
    }

    // 2. Find or create user
    UserDto userDto =
        userService
            .findOrCreateUser(mobileNumber)
            .orElseThrow(() -> new BadCredentialsException("User creation failed"));

    // 3. Convert to UserDetails (RBAC applied)
    UserDetails userDetails = userService.toUserDetails(userDto);

    // 4. Authentication success
    return new OtpAuthenticationToken(userDetails);
  }

  @Override
  public boolean supports(Class<?> auth) {
    return OtpAuthenticationToken.class.isAssignableFrom(auth);
  }
}
