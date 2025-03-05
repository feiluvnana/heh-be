package com.happyeduhub.backend.core.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleOutDto<T> {
  private T data;
}
