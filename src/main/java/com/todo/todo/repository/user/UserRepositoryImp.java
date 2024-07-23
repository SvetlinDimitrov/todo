package com.todo.todo.repository.user;

import com.todo.todo.model.entity.UserEntity;
import com.todo.todo.model.views.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

  private final UserRepositoryJpa userRepositoryJpa;

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepositoryJpa.findByEmail(email)
        .map(this::toUser);
  }


  private User toUser(UserEntity userEntity) {
    return new User(userEntity.getEmail(), userEntity.getPassword());
  }
}
