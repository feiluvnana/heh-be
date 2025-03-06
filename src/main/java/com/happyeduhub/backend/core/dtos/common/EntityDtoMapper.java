package com.happyeduhub.backend.core.dtos.common;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.happyeduhub.backend.entities.UserEntity;

@Component
@Mapper
public interface EntityDtoMapper {
  UserDto user(UserEntity entity);
}
