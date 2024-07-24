package com.internship.todo.feature.user.service;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.entity.UserEntity;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.infrastructure.shared.mappers.UserMapper;
import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
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

    if (userRepository.findByEmail(dto.email()).isPresent())
      throw new BadResponseException("User already exists");

    UserEntity userToSave = userMapper.toUserEntity(dto);
    userToSave.setPassword(passwordEncoder.encode(dto.password()));

    userRepository.save(userToSave);
  }

}
