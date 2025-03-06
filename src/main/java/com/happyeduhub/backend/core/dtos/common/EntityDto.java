package com.happyeduhub.backend.core.dtos.common;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class EntityDto {
  private UUID id;
  private Instant createdAt;
  private Instant updatedAt;
  private int version;
}
