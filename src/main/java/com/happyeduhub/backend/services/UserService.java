package com.happyeduhub.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.happyeduhub.backend.core.dtos.common.EntityDtoMapper;
import com.happyeduhub.backend.core.dtos.common.UserDto;
import com.happyeduhub.backend.core.dtos.exception.ExceptionDto;
import com.happyeduhub.backend.core.dtos.in.RegisterInDto;
import com.happyeduhub.backend.core.dtos.out.SingleOutDto;
import com.happyeduhub.backend.core.enums.UserRole;
import com.happyeduhub.backend.entities.UserEntity;
import com.happyeduhub.backend.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  @Lazy
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Override
  public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Không thể tìm thấy tài khoản `" + username + "`."));
  }

  public SingleOutDto<UserDto> create(RegisterInDto registerInDto) throws ExceptionDto {
    UserEntity user = userRepository.findByUsername(registerInDto.getUsername())
        .orElse(null);
    if (user != null) {
      throw ExceptionDto.badRequest("Tài khoản `" + registerInDto.getUsername() + "` đã tồn tại.");
    }
    user = userRepository.save(UserEntity.builder()
        .username(registerInDto.getUsername())
        .password(passwordEncoder.encode(registerInDto.getPassword()))
        .role(UserRole.STUDENT)
        .build());
    return new SingleOutDto<UserDto>(entityDtoMapper.user(user));
  }
}
