package com.happyeduhub.backend.core.dtos.out;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipleOutDto<T> {
  private List<T> data;
}