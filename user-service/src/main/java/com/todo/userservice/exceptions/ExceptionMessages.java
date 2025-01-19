package com.todo.userservice.exceptions;

import com.todo.exceptions.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages implements ExceptionMessage {
  USER_DETAILS_NOT_FOUND("User details not found with id: %s", "User not found"),
  USER_NOT_FOUND("User not found with id: %s", "User not found"),
  ;

  private final String message;
  private final String title;
}
