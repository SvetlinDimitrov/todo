package com.todo.todo.repository.user;

import com.todo.todo.model.views.User;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findByEmail(String email);
}
