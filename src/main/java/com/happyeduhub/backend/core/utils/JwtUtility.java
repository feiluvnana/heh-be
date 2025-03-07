package com.happyeduhub.backend.core.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.happyeduhub.backend.core.enums.UserRole;
import com.happyeduhub.backend.dtos.exceptions.ExceptionDto;
import com.happyeduhub.backend.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
  private String secretKey = "e2d303d36a13c1ffa22a15437675c13dac0dd536ca77826cb6319a334e47ac4492a8ae2fa6af203695b6fc311c58f7f49d77011d46b1763559d3be9e799f27e5bfc130971d303f6c97324605dc14b81d1c7cb07ff13bccf361323b4a3b6b6d19f955ca78f0d8517c19ef0c9bd95bf720e56d8db7b5243c32eea6bffd3084d6a5fda7313c88a6358e4d2d3dc93a6e539e4e8ea565cb92984e1818e93e8b6a478093980c9115435d3d788a38bc376eeadde250fee21d0e1f14e23f918f54e8dede976f4368a439c26589ebe4c6fe7193a627eebda812b9736f0bcef3be31cd40b8c82514d9729dbb6ced077817615948d11fd343cc5041e75efa75e173d4993622";
  private long expiresIn = 14 * 24 * 60 * 60 * 1000;

  public String generateToken(UserEntity user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .claim("role", user.getRole().name())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiresIn))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
        .compact();
  }

  private Claims getClaimsFromToken(String token) throws ExceptionDto {
    try {
      final Claims claims = Jwts.parser()
          .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
          .build()
          .parseSignedClaims(token)
          .getPayload();
      return claims;
    } catch (UnsupportedJwtException ex) {
      throw ExceptionDto.unauthorized("Định dạng token không được hỗ trợ.");
    } catch (IllegalArgumentException ex) {
      throw ExceptionDto.unauthorized("Token không hợp lệ.");
    } catch (ExpiredJwtException ex) {
      throw ExceptionDto.unauthorized("Token đã hết hạn.");
    } catch (Exception ex) {
      throw ExceptionDto.unauthorized("Không thể xác thực token.");
    }
  }

  public String getUsernameFromToken(String token) throws ExceptionDto {
    return getClaimsFromToken(token).getSubject();
  }

  public UserRole getRoleFromToken(String token) throws ExceptionDto {
    return UserRole.valueOf(getClaimsFromToken(token).get("role", String.class));
  }
}
