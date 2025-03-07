package com.happyeduhub.backend.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.happyeduhub.backend.dtos.entities.UserDto;
import com.happyeduhub.backend.dtos.ins.RegisterInDto;
import com.happyeduhub.backend.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface DtoMapper {
  UserDto userEntityToUserDto(UserEntity entity);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "code", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "isThaiphien", source = "thaiphien")
  UserEntity registerInDtoToUserEntity(RegisterInDto dto);
}
