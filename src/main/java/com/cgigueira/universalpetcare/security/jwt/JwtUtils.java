package com.cgigueira.universalpetcare.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.security.user.PetCareUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.PostConstruct;

@Service
public class JwtUtils {

  private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  private SecretKey secretKey;

  @Value("${security.jwt.secret}")
  private String jwtSecret;

  @Value("${security.jwt.expiration}")
  private Long expirationTime;

  @PostConstruct
  public void init() {
    byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
    this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
  }

  public String generateToken(Authentication authentication) {
    PetCareUserDetails userPrincipal = (PetCareUserDetails) authentication.getPrincipal();
    List<String> roles = userPrincipal.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .toList();
    return Jwts.builder()
        .subject(userPrincipal.getUsername())
        .claim("roles", roles)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + this.expirationTime))
        .signWith(this.secretKey)
        .compact();
  }
  
  public String getUsernameFromToken(String token) {
    return Jwts.parser()
        .verifyWith(this.secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }
  
  public boolean isTokenValid(String token) {
    try {
      Jwts.parser()
        .verifyWith(this.secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration().before(new Date());
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT", e.getMessage());
      return false;
    } catch (ExpiredJwtException e) {
      logger.error("Expired JWT", e.getMessage());
      return false;
    } catch (UnsupportedJwtException e) {
      logger.error("Unsupported JWT", e.getMessage());
      return false;
    } catch (IllegalArgumentException e) {
      logger.error("Empty JWT", e.getMessage());
      return false;
    }
  }

}
