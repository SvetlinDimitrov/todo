package com.internship.todo.feature.project.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectPostPutRequest(

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    String name

) {
}
