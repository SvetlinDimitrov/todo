package com.internship.todo.feature.task.dto;

import java.time.LocalDateTime;

public record TaskView(
    Long id,
    String name,
    String description,
    Boolean done,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long projectId) {
}
