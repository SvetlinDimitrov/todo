package com.internship.todo.feature.task.utils.validators;

import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.feature.task.dto.TaskPostRequest;

public class CreateTaskValidator {

  public static void validate(TaskPostRequest dto) {
    if (dto.name() == null || dto.name().trim().length() < 3) {
      throw new BadResponseException("Name must be at least 3 characters long");
    }

    if (dto.name().trim().length() > 255) {
      throw new BadResponseException("Name must be at most 255 characters long");
    }
  }
}
