package com.internship.todo.feature.user.service;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.dto.UserView;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.feature.user.utils.mappers.UserMapper;
import com.internship.todo.feature.user.utils.validators.CreateUserValidator;
import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void createUser(UserPostRequest dto) {
    CreateUserValidator.validate(dto);

    if (userRepository.findByEmail(dto.email()).isPresent())
      throw new BadResponseException("User already exists");

    UserView user = toUser(dto);

    userRepository.save(UserMapper.toUserEntity(user));
  }

  private UserView toUser(UserPostRequest dto) {
    return new UserView(dto.email(), passwordEncoder.encode(dto.password()));
  }
}
