package com.happyeduhub.backend.dtos.ins;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginInDto {
  @Email(message = "`username` phải là địa chỉ email hợp lệ.")
  @NotBlank(message = "Cần phải nhập `username`.")
  private String username;

  @Length(min = 6, message = "`password` phải có ít nhất 6 ký tự.")
  @NotBlank(message = "Cần phải nhập `password`.")
  private String password;
}