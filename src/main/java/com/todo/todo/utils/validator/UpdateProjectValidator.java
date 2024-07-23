package com.todo.todo.utils.validator;

import com.todo.todo.model.dto.UpdateProject;

public class UpdateProjectValidator {

  public static void validate(UpdateProject dto) {
    if (dto.name() == null || dto.name().trim().length() < 3) {
      throw new IllegalArgumentException("Name must be at least 3 characters long");
    }

    if (dto.name().trim().length() > 255) {
      throw new IllegalArgumentException("Name must be at most 255 characters long");
    }
  }
}
