package com.happyeduhub.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyeduhub.backend.core.dtos.common.UserDto;
import com.happyeduhub.backend.core.dtos.exception.ExceptionDto;
import com.happyeduhub.backend.core.dtos.in.RegisterInDto;
import com.happyeduhub.backend.core.dtos.out.SingleOutDto;
import com.happyeduhub.backend.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<SingleOutDto<UserDto>> register(@RequestBody @Valid RegisterInDto dto)
      throws ExceptionDto {
    return ResponseEntity.ok(userService.create(dto));
  }
}
