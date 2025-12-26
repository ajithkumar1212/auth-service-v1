package com.auth_service_v1.security.token;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RefreshTokenAuthenticationToken extends AbstractAuthenticationToken {

  private final Object principal;
  private final String refreshToken;

  /** Used BEFORE authentication */
  public RefreshTokenAuthenticationToken(String refreshToken) {
    super(null);
    this.principal = null;
    this.refreshToken = refreshToken;
    setAuthenticated(false);
  }

  /** Used AFTER authentication */
  public RefreshTokenAuthenticationToken(
      Object principal, String refreshToken, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.refreshToken = refreshToken;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return refreshToken;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }
}
