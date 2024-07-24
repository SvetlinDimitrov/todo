package com.internship.todo.feature.task.dto;

import jakarta.validation.constraints.Min;

public record TaskPageableRequest(

    @Min(value = 0, message = "Page number must be greater than or equal to 0")
    Integer page,

    @Min(value = 1, message = "Page size must be greater than 0")
    Integer size
) {
}
