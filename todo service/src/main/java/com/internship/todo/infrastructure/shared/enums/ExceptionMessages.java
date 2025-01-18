package com.internship.todo.infrastructure.shared.enums;

import lombok.Getter;

@Getter
public enum ExceptionMessages {
  PROJECT_NOT_FOUND("Project with id %d not found for user with email %s"),
  USER_NOT_FOUND("User with email %s not found"),
  TASK_NOT_FOUND("Task with id %d not found for user with email %s"),
  USER_ALREADY_EXISTS("User with email %s already exists");

  private final String message;

  ExceptionMessages(String message) {
    this.message = message;
  }
}
