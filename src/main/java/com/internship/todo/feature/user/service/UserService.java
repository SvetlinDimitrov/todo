package com.internship.todo.feature.user.service;

import com.internship.todo.feature.user.dto.UserPostRequest;

public interface UserService {

  void createUser(UserPostRequest dto);
}
