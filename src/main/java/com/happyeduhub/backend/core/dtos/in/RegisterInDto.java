package com.happyeduhub.backend.core.dtos.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RegisterInDto {
  @NotBlank(message = "Cần phải nhập `username`.")
  private String username;

  @NotBlank(message = "Cần phải nhập `password`.")
  private String password;
}