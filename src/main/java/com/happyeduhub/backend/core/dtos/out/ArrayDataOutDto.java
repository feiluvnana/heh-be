package com.happyeduhub.backend.core.dtos.out;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class ArrayDataOutDto<T> extends BaseOutDto {
  private List<T> data;
}