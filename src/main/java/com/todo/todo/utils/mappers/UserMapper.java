package com.todo.todo.utils.mappers;

import com.todo.todo.model.entity.UserEntity;
import com.todo.todo.model.views.User;

import java.util.ArrayList;

public class UserMapper {

  public static User toUser(UserEntity userEntity) {
    return new User(userEntity.getEmail(), userEntity.getPassword());
  }

  public static UserEntity toUserEntity(User user) {
    return new UserEntity(user.email(), user.password(), new ArrayList<>());
  }
}
