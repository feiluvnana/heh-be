package com.happyeduhub.backend.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.happyeduhub.backend.core.dtos.exception.ExceptionDto;

@RestControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ExceptionDto> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    final ExceptionDto errors = ExceptionDto.builder()
        .type("METHOD_NOT_ALLOWED_EXCEPTION")
        .message("Không hỗ trợ phương thức " + ex.getMethod() + " tại đường dẫn này.")
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .build();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errors);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionDto> handleNoResourceFoundException(
      NoResourceFoundException ex) {
    final ExceptionDto exception = ExceptionDto.builder()
        .type("NOT_FOUND_EXCEPTION")
        .message("Không tồn tại đường dẫn này.")
        .status(HttpStatus.NOT_FOUND.value())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    final ExceptionDto exception = ExceptionDto.builder()
        .type("VALIDATION_EXCEPTION")
        .message(String.join("\n", ex.getAllErrors().stream().map((e) -> e.getDefaultMessage()).toList()))
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .build();
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleUsernameNotFoundException(
      UsernameNotFoundException ex) {
    final ExceptionDto exception = ExceptionDto.builder()
        .type("USERNAME_NOT_FOUND_EXCEPTION")
        .message(ex.getMessage())
        .status(HttpStatus.NOT_FOUND.value())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
  }

  @ExceptionHandler(ExceptionDto.class)
  public ResponseEntity<ExceptionDto> handleExceptionDto(ExceptionDto ex) {
    return ResponseEntity.status(ex.getStatus()).body(ex);
  }
}
