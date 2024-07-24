package com.internship.todo.feature.task.service;

import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import org.springframework.data.domain.Page;

public interface TaskService {

  Page<TaskView> getTasks(Long projectId, int page, int size);

  TaskView getTask(Long id);

  void createTask(TaskPostRequest dto, Long projectId);

  void updateTask(Long id, TaskPutRequest dto);

  void deleteTask(Long id);
}
