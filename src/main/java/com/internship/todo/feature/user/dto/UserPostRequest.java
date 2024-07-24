package com.internship.todo.feature.user.dto;

import jakarta.validation.constraints.*;

public record UserPostRequest(

    @NotNull(message = "Email must not be blank")
    @NotBlank(message = "Email must not be blank")
    @NotEmpty(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    String email,

    @NotNull(message = "Password must not be blank")
    @NotBlank(message = "Password must not be blank")
    @NotEmpty(message = "Password must not be blank")
    @Size(max = 255, message = "Password must not exceed 255 characters")
    String password
) {
}