package com.todo.todo.exceptions;

public class BadResponseException extends RuntimeException {

  public BadResponseException(String message) {
    super(message);
  }
}
