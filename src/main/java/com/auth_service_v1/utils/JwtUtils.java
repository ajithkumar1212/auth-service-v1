package com.auth_service_v1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUtils {

  public static Key getSignInKey(String secret) {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public static Collection<GrantedAuthority> getAuthorities(String token, String secret) {
    Claims claims = JwtUtils.getAllClaims(token, secret);
    Object rawAuthorities = claims.get("authorities");
    if (!(rawAuthorities instanceof List<?> list)) {
      return Collections.emptySet();
    }
    return list.stream()
        .filter(Objects::nonNull)
        .map(Object::toString)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());
  }

  public static String generateAccessToken(Authentication auth, String secret, long expirationMs) {
    return Jwts.builder()
        .subject(auth.getName())
        .claim(
            "authorities",
            auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusMillis(expirationMs)))
        .signWith(getSignInKey(secret))
        .compact();
  }

  public static String generateRefreshToken(
      Authentication auth, String refreshSecret, long refreshExpirationMs) {

    return Jwts.builder()
        .subject(auth.getName())
        .issuedAt(Date.from(Instant.now()))
        .claim("type", "refresh")
        .expiration(Date.from(Instant.now().plusMillis(refreshExpirationMs)))
        .signWith(getSignInKey(refreshSecret))
        .compact();
  }

  public static String getUsernameFromToken(String token, String secret) {
    return getClaimFromToken(secret, token, Claims::getSubject);
  }

  public static Claims getAllClaims(String token, String secret) {
    return getAllClaimsFromToken(secret, token);
  }

  public static <T> T getClaimFromToken(
      String secret, String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(secret, token);
    return claimsResolver.apply(claims);
  }

  private static Claims getAllClaimsFromToken(String secret, String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSignInKey(secret))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public static boolean isvalidate(String token, String secret) {
    try {
      getAllClaimsFromToken(secret, token);
      return !isTokenExpired(secret, token);
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean isTokenExpired(String secret, String token) {
    final Date expiration = getExpirationDateFromToken(secret, token);
    return expiration.before(new Date());
  }

  public static Date getExpirationDateFromToken(String secret, String token) {
    return getClaimFromToken(secret, token, Claims::getExpiration);
  }
}
