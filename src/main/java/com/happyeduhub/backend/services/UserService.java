package com.happyeduhub.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.happyeduhub.backend.core.enums.UserRole;
import com.happyeduhub.backend.core.utils.JwtUtility;
import com.happyeduhub.backend.dtos.DtoMapper;
import com.happyeduhub.backend.dtos.entities.UserDto;
import com.happyeduhub.backend.dtos.exceptions.ExceptionDto;
import com.happyeduhub.backend.dtos.ins.LoginInDto;
import com.happyeduhub.backend.dtos.ins.RegisterInDto;
import com.happyeduhub.backend.dtos.outs.LoginOutDto;
import com.happyeduhub.backend.dtos.outs.SingleItemOutDto;
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
  private JwtUtility jwtUtility;

  @Autowired
  private DtoMapper dtoMapper;

  @Override
  public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Không thể tìm thấy tài khoản `" + username + "`."));
  }

  public SingleItemOutDto<UserDto> create(RegisterInDto registerInDto) throws ExceptionDto {
    UserEntity user = userRepository.findByUsername(registerInDto.getUsername())
        .orElse(null);
    if (user != null) {
      throw ExceptionDto.badRequest("Tài khoản `" + registerInDto.getUsername() + "` đã tồn tại.");
    }
    user = dtoMapper.registerInDtoToUserEntity(registerInDto);
    user.setPassword(passwordEncoder.encode(registerInDto.getPassword()));
    user.setRole(UserRole.STUDENT);
    user = userRepository.save(user);
    return new SingleItemOutDto<UserDto>(dtoMapper.userEntityToUserDto(user));
  }

  public LoginOutDto login(LoginInDto loginInDto) throws ExceptionDto {
    UserEntity user = userRepository.findByUsername(loginInDto.getUsername())
        .orElseThrow(
            () -> ExceptionDto.unauthorized("Tài khoản hoặc mật khẩu không chính xác."));
    if (!passwordEncoder.matches(loginInDto.getPassword(), user.getPassword())) {
      throw ExceptionDto.unauthorized("Tài khoản hoặc mật khẩu không chính xác.");
    }
    return new LoginOutDto(dtoMapper.userEntityToUserDto(user), jwtUtility.generateToken(user));
  }

  public SingleItemOutDto<UserDto> profile() throws ExceptionDto {
    UserEntity user = loadUserByUsername(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    return new SingleItemOutDto<UserDto>(dtoMapper.userEntityToUserDto(user));
  }
}
