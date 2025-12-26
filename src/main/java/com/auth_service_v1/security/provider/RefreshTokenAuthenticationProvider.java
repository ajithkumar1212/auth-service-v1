package com.auth_service_v1.security.provider;

import com.auth_service_v1.dto.user.UserDto;
import com.auth_service_v1.entity.authentication.RefreshToken;
import com.auth_service_v1.security.token.RefreshTokenAuthenticationToken;
import com.auth_service_v1.service.impl.auth.RefreshTokenService;
import com.auth_service_v1.service.user.IUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("refreshTokenAuthenticationProvider")
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

  private final RefreshTokenService refreshTokenService;
  private final IUserService userService;

  public RefreshTokenAuthenticationProvider(
      RefreshTokenService refreshTokenService, IUserService userService) {
    this.refreshTokenService = refreshTokenService;
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) {

    String refreshToken = (String) authentication.getCredentials();

    RefreshToken rt = refreshTokenService.validate(refreshToken);

    // 2. Find or create user
    UserDto userDto =
        userService
            .findUser(rt.getUsername())
            .orElseThrow(() -> new BadCredentialsException("User creation failed"));

    // 3. Convert to UserDetails (RBAC applied)
    UserDetails userDetails = userService.toUserDetails(userDto);

    return new RefreshTokenAuthenticationToken(
        userDetails, refreshToken, userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> auth) {
    return RefreshTokenAuthenticationToken.class.isAssignableFrom(auth);
  }
}
