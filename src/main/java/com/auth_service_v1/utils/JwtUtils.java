package com.auth_service_v1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;

public class JwtUtils {

  private static Key getSignInKey(String secret) {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public static String generateToken(
      String secret, Map<String, Object> claims, String subject, long expirationMs) {
    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusMillis(expirationMs)))
        .signWith(getSignInKey(secret))
        .compact();
  }

  public static String getUsernameFromToken(String secret, String token) {
    return getClaimFromToken(secret, token, Claims::getSubject);
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

  public static boolean validateToken(String secret, String token) {
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
