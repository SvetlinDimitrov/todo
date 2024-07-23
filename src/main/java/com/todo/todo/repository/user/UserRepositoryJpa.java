package com.todo.todo.repository.user;

import com.todo.todo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);
}
