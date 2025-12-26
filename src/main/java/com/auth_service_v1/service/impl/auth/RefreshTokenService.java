package com.auth_service_v1.service.impl.auth;

import com.auth_service_v1.entity.authentication.RefreshToken;
import com.auth_service_v1.repository.authentication.RefreshTokenRepository;
import java.time.Instant;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefreshTokenService {

  private final RefreshTokenRepository repository;

  public RefreshTokenService(RefreshTokenRepository repository) {
    this.repository = repository;
  }

  public RefreshToken create(String username, String token, long expiryMs) {
    RefreshToken refreshToken = repository.findByUsername(username).orElse(null);
    if (refreshToken == null) {
      refreshToken = new RefreshToken();
      refreshToken.setUsername(username);
    }
    refreshToken.setToken(token);
    refreshToken.setExpiryDate(Instant.now().plusMillis(expiryMs));
    return repository.save(refreshToken);
  }

  public RefreshToken validate(String token) {
    RefreshToken rt =
        repository
            .findByToken(token)
            .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

    if (rt.isRevoked()) {
      throw new BadCredentialsException("Refresh token revoked");
    }

    if (rt.getExpiryDate().isBefore(Instant.now())) {
      throw new BadCredentialsException("Refresh token expired");
    }

    return rt;
  }

  public void revoke(String token) {
    repository
        .findByToken(token)
        .ifPresent(
            rt -> {
              rt.setRevoked(true);
              repository.save(rt);
            });
  }

  public void revokeAllForUser(String username) {
    repository.findAllByUsernameAndRevokedFalse(username).forEach(rt -> rt.setRevoked(true));
  }
}
