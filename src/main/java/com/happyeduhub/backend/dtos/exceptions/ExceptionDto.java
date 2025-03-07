package com.happyeduhub.backend.dtos.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@JsonIncludeProperties(value = { "message", "status", "type" })
public class ExceptionDto extends Exception {
  private String message;
  private int status;
  private String type;

  public static ExceptionDto badRequest(String message) {
    return new ExceptionDto(message, HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST_EXCEPTION");
  }

  public static ExceptionDto unauthorized(String message) {
    return new ExceptionDto(message, HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED_EXCEPTION");
  }

  public static ExceptionDto forbidden(String message) {
    return new ExceptionDto(message, HttpStatus.FORBIDDEN.value(), "FORBIDDEN_EXCEPTION");
  }

  public static ExceptionDto notFound(String message) {
    return new ExceptionDto(message, HttpStatus.NOT_FOUND.value(), "NOT_FOUND_EXCEPTION");
  }

  public static ExceptionDto methodNotAllowed(String message) {
    return new ExceptionDto(message, HttpStatus.METHOD_NOT_ALLOWED.value(),
        "METHOD_NOT_ALLOWED_EXCEPTION");
  }

  public static ExceptionDto unprocessableEntity(String message) {
    return new ExceptionDto(message, HttpStatus.UNPROCESSABLE_ENTITY.value(),
        "UNPROCESSABLE_ENTITY_EXCEPTION");
  }

  public static ExceptionDto validation(String message) {
    return new ExceptionDto(message, HttpStatus.UNPROCESSABLE_ENTITY.value(),
        "VALIDATION_EXCEPTION");
  }

  public static ExceptionDto internalServerError(String message) {
    return new ExceptionDto(message, HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "INTERNAL_SERVER_ERROR_EXCEPTION");
  }
}
