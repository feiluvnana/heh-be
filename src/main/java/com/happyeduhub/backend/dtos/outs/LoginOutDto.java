package com.happyeduhub.backend.dtos.outs;

import com.happyeduhub.backend.dtos.entities.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginOutDto {
  private UserDto user;
  private String token;
}
