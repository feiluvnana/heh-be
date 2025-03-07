package com.happyeduhub.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyeduhub.backend.dtos.entities.UserDto;
import com.happyeduhub.backend.dtos.exceptions.ExceptionDto;
import com.happyeduhub.backend.dtos.ins.LoginInDto;
import com.happyeduhub.backend.dtos.ins.RegisterInDto;
import com.happyeduhub.backend.dtos.outs.LoginOutDto;
import com.happyeduhub.backend.dtos.outs.SingleItemOutDto;
import com.happyeduhub.backend.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<SingleItemOutDto<UserDto>> register(@RequestBody @Valid RegisterInDto dto)
      throws ExceptionDto {
    return ResponseEntity.ok(userService.create(dto));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginOutDto> login(@RequestBody @Valid LoginInDto dto) throws ExceptionDto {
    return ResponseEntity.ok(userService.login(dto));
  }
}
