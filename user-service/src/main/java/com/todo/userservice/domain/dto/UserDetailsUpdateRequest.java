package com.todo.userservice.domain.dto;

import jakarta.validation.constraints.Size;

public record UserDetailsUpdateRequest(

    @Size(max = 255, message = "Username must not exceed 255 characters")
    String username
) {

}