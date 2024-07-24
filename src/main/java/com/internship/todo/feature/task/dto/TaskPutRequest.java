package com.internship.todo.feature.task.dto;

public record TaskPutRequest(String name, String description, boolean done) {
}
