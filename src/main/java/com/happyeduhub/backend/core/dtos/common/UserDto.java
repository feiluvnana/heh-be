package com.happyeduhub.backend.core.dtos.common;

import com.happyeduhub.backend.core.enums.UserRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class UserDto extends EntityDto {
  private String username;
  private UserRole role;
}
