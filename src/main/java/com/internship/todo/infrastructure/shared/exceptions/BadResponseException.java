package com.internship.todo.infrastructure.shared.exceptions;

public class BadResponseException extends RuntimeException {

  public BadResponseException(String message) {
    super(message);
  }
}
