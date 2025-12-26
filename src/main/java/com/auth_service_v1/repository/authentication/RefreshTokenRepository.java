package com.auth_service_v1.repository.authentication;

import com.auth_service_v1.entity.authentication.RefreshToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByToken(String token);

  Optional<RefreshToken> findByUsername(String username);

  void deleteByUsername(String username);

  List<RefreshToken> findAllByUsernameAndRevokedFalse(String username);
}
