package com.happyeduhub.backend.dtos.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.happyeduhub.backend.core.enums.UserGender;
import com.happyeduhub.backend.core.enums.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDto extends EntityDto {
  private String username;
  private UserRole role;
  private UserGender gender;

  @JsonProperty("isThaiphien")
  private boolean isThaiphien;

  @Schema(types = { "string", "null" })
  private String tel;

  @Schema(types = { "string", "null" })
  private String avatar;

  @Schema(types = { "string", "null" }, format = "date-time")
  private Instant dob;

  @Schema(types = { "string", "null" })
  private String code;

  @Schema(types = { "string", "null" })
  private String address;
}
