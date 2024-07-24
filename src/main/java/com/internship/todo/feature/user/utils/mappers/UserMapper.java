package com.internship.todo.feature.user.utils.mappers;

import com.internship.todo.feature.user.entity.UserEntity;
import com.internship.todo.feature.user.dto.UserView;

import java.util.ArrayList;

public class UserMapper {

  public static UserView toUser(UserEntity userEntity) {
    return new UserView(userEntity.getEmail(), userEntity.getPassword());
  }

  public static UserEntity toUserEntity(UserView user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());
    userEntity.setProjects(new ArrayList<>());
    return userEntity;
  }
}
