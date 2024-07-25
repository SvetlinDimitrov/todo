package com.internship.todo.feature.user.service;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.entity.User;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.infrastructure.shared.enums.ExceptionMessages;
import com.internship.todo.infrastructure.shared.exceptions.UserAlreadyExistsException;
import com.internship.todo.infrastructure.shared.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public void createUser(UserPostRequest dto) {

    if (userRepository.existsByEmail(dto.email()))
      throw new UserAlreadyExistsException(
          String.format(ExceptionMessages.USER_ALREADY_EXISTS.getMessage(), dto.email())
      );

    User userToSave = userMapper.toUserEntity(dto);
    userToSave.setPassword(passwordEncoder.encode(dto.password()));

    userRepository.save(userToSave);
  }

}
