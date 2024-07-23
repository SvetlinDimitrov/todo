package com.todo.todo.service;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.CreateUser;
import com.todo.todo.model.views.User;
import com.todo.todo.repository.user.UserRepository;
import com.todo.todo.utils.validator.CreateUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void createUser(CreateUser dto) {
    CreateUserValidator.validate(dto);

    if (userRepository.findByEmail(dto.email()).isPresent())
      throw new BadResponseException("User already exists");

    userRepository.saveUser(toUser(dto));
  }

  private User toUser(CreateUser dto) {
    return new User(dto.email(), passwordEncoder.encode(dto.password()));
  }
}
