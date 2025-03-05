package com.happyeduhub.backend.core.dtos.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class SingleDataOutDto<T> extends BaseOutDto {
  private T data;
}
