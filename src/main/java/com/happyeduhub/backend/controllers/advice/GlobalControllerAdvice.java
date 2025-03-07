package com.happyeduhub.backend.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.happyeduhub.backend.dtos.exceptions.ExceptionDto;

@RestControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ExceptionDto> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    final ExceptionDto errors = ExceptionDto.methodNotAllowed(
        "Không hỗ trợ phương thức " + ex.getMethod() + " tại đường dẫn này.");
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errors);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionDto> handleNoResourceFoundException(
      NoResourceFoundException ex) {
    final ExceptionDto errors = ExceptionDto.notFound("Không tồn tại đường dẫn này.");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    final ExceptionDto errors = ExceptionDto.validation(String.join("\n", ex.getAllErrors().stream()
        .map((e) -> e.getDefaultMessage()).toList()));
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    if (ex.getCause() instanceof InvalidFormatException) {
      InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
      final ExceptionDto errors = ExceptionDto.validation("Giá trị `" + invalidFormatException.getValue()
          + "` không thể gán cho kiểu dữ liệu `" + invalidFormatException.getTargetType().getSimpleName()
          + "`.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    final ExceptionDto errors = ExceptionDto.validation("Không thể đọc dữ liệu đầu vào.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleUsernameNotFoundException(
      UsernameNotFoundException ex) {
    final ExceptionDto errors = ExceptionDto.notFound(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
  }

  @ExceptionHandler(ExceptionDto.class)
  public ResponseEntity<ExceptionDto> handleExceptionDto(ExceptionDto ex) {
    return ResponseEntity.status(ex.getStatus()).body(ex);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Exception> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
  }
}
