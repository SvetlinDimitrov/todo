package com.todo.todo.repository.user;

import com.todo.todo.model.views.User;
import com.todo.todo.utils.mappers.UserMapper;
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
        .map(UserMapper::toUser);
  }

  @Override
  public void saveUser(User user) {
    userRepositoryJpa.save(UserMapper.toUserEntity(user));
  }
}
