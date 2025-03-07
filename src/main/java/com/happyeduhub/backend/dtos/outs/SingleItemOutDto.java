package com.happyeduhub.backend.dtos.outs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleItemOutDto<T> {
  private T data;
}
