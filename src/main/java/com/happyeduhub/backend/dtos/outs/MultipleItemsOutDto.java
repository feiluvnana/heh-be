package com.happyeduhub.backend.dtos.outs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultipleItemsOutDto<T> {
  private List<T> data;
}