package com.happyeduhub.backend.core.dtos.common;

import java.time.Instant;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EntityDto {
  @Getter(AccessLevel.PUBLIC)
  private UUID id;

  @Getter(AccessLevel.PUBLIC)
  private Instant createdAt;

  @Getter(AccessLevel.PUBLIC)
  private Instant updatedAt;

  @Getter(AccessLevel.PUBLIC)
  private int version;
}
