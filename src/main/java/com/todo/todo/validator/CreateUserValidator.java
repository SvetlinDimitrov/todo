package com.todo.todo.validator;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.CreateUser;

public class CreateUserValidator {

  public static void validate(CreateUser createUser) {
    if (createUser.email() == null || createUser.email().isBlank())
      throw new BadResponseException("Email must not be blank");

    if (createUser.email().length() > 255)
      throw new BadResponseException("Email must not exceed 255 characters");

    if (createUser.password() == null || createUser.password().isBlank())
      throw new BadResponseException("Password must not be blank");

    if (createUser.password().length() > 255)
      throw new BadResponseException("Password must not exceed 255 characters");
  }

}
