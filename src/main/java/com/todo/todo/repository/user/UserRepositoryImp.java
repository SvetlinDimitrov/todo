package com.todo.todo.repository.user;

import com.todo.todo.model.entity.UserEntity;
import com.todo.todo.model.views.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

  @Override
  public void saveUser(User user) {
    userRepositoryJpa.save(toUserEntity(user));
  }


  private User toUser(UserEntity userEntity) {
    return new User(userEntity.getEmail(), userEntity.getPassword());
  }

  private UserEntity toUserEntity(User user) {
    return new UserEntity(user.email(), user.password(), new ArrayList<>());
  }
}
