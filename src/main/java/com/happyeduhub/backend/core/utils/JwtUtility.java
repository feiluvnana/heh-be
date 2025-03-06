package com.happyeduhub.backend.core.utils;

import java.util.Date;

import com.happyeduhub.backend.core.dtos.exception.ExceptionDto;
import com.happyeduhub.backend.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

public class JwtUtility {
  private String secretKey = "secret";
  private long expiresIn = 14 * 24 * 60 * 60 * 1000;

  public String generateToken(UserEntity user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiresIn))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .compact();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public String getUsernameFromToken(String token) throws ExceptionDto {
    Claims claims;
    try {
      claims = getClaimsFromToken(token);
    } catch (UnsupportedJwtException ex) {
      throw ExceptionDto.unauthorized("Định dạng token không được hỗ trợ.");
    } catch (IllegalArgumentException ex) {
      throw ExceptionDto.unauthorized("Token không hợp lệ.");
    } catch (ExpiredJwtException ex) {
      throw ExceptionDto.unauthorized("Token đã hết hạn.");
    } catch (Exception ex) {
      throw ExceptionDto.unauthorized("Không thể xác thực token.");
    }
    return claims.getSubject();
  }
}
