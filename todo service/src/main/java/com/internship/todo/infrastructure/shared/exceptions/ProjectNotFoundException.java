package com.internship.todo.infrastructure.shared.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectNotFoundException extends RuntimeException {

  public ProjectNotFoundException(String message) {
    super(message);
  }
}
