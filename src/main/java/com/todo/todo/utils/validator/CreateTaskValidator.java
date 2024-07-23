package com.todo.todo.utils.validator;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.CreateTask;

public class CreateTaskValidator {

  public static void validate(CreateTask dto) {
    if (dto.name() == null || dto.name().trim().length() < 3) {
      throw new BadResponseException("Name must be at least 3 characters long");
    }

    if (dto.name().trim().length() > 255) {
      throw new BadResponseException("Name must be at most 255 characters long");
    }
  }
}
