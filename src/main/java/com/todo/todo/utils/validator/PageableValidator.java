package com.todo.todo.utils.validator;

public class PageableValidator {

  public static void validate(int page, int size) {
    if (page < 0) {
      throw new IllegalArgumentException("Page number must be greater than or equal to 0");
    }
    if (size < 1) {
      throw new IllegalArgumentException("Page size must be greater than 0");
    }
  }
}
