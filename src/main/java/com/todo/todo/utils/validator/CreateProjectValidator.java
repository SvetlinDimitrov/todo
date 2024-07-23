package com.todo.todo.utils.validator;

import com.todo.todo.model.dto.CreateProject;

public class CreateProjectValidator {

  public static void validate(CreateProject dto) {
    if (dto.name() == null || dto.name().trim().length() < 3) {
      throw new IllegalArgumentException("Name must be at least 3 characters long");
    }

    if (dto.name().trim().length() > 255) {
      throw new IllegalArgumentException("Name must be at most 255 characters long");
    }
  }
}
