package com.happyeduhub.backend.core.dtos.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonIncludeProperties(value = { "message", "status", "type" })
@Builder
public class ExceptionDto extends Exception {
  private String message;
  private int status;
  private String type;

  public static ExceptionDto badRequest(String message) {
    return ExceptionDto.builder()
        .message(message)
        .status(HttpStatus.BAD_REQUEST.value())
        .type("BAD_REQUEST_EXCEPTION")
        .build();
  }
}
