package com.internship.todo.feature.project.utils.validators;

import com.internship.todo.feature.project.dto.ProjectPutRequest;

public class UpdateProjectValidator {

  public static void validate(ProjectPutRequest dto) {
    if (dto.name() == null || dto.name().trim().length() < 3) {
      throw new IllegalArgumentException("Name must be at least 3 characters long");
    }

    if (dto.name().trim().length() > 255) {
      throw new IllegalArgumentException("Name must be at most 255 characters long");
    }
  }
}
