package com.internship.todo.feature.project.dto;

import com.internship.todo.feature.task.dto.TaskView;

import java.util.List;

public record ProjectView(Long id, String name, List<TaskView> tasks, String userEmail) {
}
