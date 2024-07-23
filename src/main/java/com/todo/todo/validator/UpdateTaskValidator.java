package com.todo.todo.validator;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.UpdateTask;

public class UpdateTaskValidator {

  public static void validate(UpdateTask dto) {
    if (dto.name() == null || dto.name().isEmpty()) {
      throw new BadResponseException("Name is required");
    }

    if (dto.name().trim().length() > 255) {
      throw new BadResponseException("Name should be less than 255 characters");
    }

    if (dto.description() != null && dto.description().trim().length() > 255) {
      throw new BadResponseException("Description should be less than 255 characters");
    }
  }
}
