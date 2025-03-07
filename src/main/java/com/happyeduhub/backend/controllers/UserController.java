package com.happyeduhub.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyeduhub.backend.dtos.entities.UserDto;
import com.happyeduhub.backend.dtos.exceptions.ExceptionDto;
import com.happyeduhub.backend.dtos.outs.SingleItemOutDto;
import com.happyeduhub.backend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/profile")
  public ResponseEntity<SingleItemOutDto<UserDto>> profile() throws ExceptionDto {
    return ResponseEntity.ok(userService.profile());
  }
}
