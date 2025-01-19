package com.todo.userservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDetailsCreateRequest(

    @NotBlank(message = "Username cannot be blank")
    @Size(max = 255, message = "Username must not exceed 255 characters")
    String username
) {
}