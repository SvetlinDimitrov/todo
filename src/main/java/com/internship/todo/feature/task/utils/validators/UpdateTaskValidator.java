package com.internship.todo.feature.task.utils.validators;

import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.feature.task.dto.TaskPutRequest;

public class UpdateTaskValidator {

  public static void validate(TaskPutRequest dto) {
    if (dto.name() == null || dto.name().isEmpty()) {
      throw new BadResponseException("Name is required");
    }

    if (dto.name().trim().length() > 255) {
      throw new BadResponseException("Name should be less than 255 characters");
    }

    if (dto.description() == null || dto.description().trim().length() > 255) {
      throw new BadResponseException("Description should be less than 255 characters");
    }
  }
}
