package com.happyeduhub.backend.dtos.ins;

import java.time.Instant;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.happyeduhub.backend.core.enums.UserGender;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class RegisterInDto {
  @Email(message = "`username` phải là địa chỉ email hợp lệ.")
  @NotBlank(message = "Cần phải nhập `username`.")
  private String username;

  @Length(min = 6, message = "`password` phải có ít nhất 6 ký tự.")
  @NotBlank(message = "Cần phải nhập `password`.")
  private String password;

  @NotNull(message = "Cần phải chọn `gender`.")
  private UserGender gender;

  @NotNull(message = "Cần phải chọn `isThaiphien`.")
  @JsonProperty("isThaiphien")
  private boolean isThaiphien;

  @Length(min = 10, max = 10, message = "`tel` phải có 10 số.")
  @Schema(types = { "string", "null" })
  private String tel;

  @URL(message = "`avatar` phải là địa chỉ URL hợp lệ.")
  @Schema(types = { "string", "null" })
  private String avatar;

  @Past(message = "`dob` phải là ngày tháng trong quá khứ.")
  @Schema(types = { "string", "null" }, format = "date-time")
  private Instant dob;

  @Length(max = 255, message = "`address` phải có tối đa 255 ký tự.")
  @Schema(types = { "string", "null" })
  private String address;
}