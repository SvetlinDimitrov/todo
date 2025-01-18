package com.internship.todo.feature.task.service;

import com.internship.todo.feature.task.dto.TaskFilter;
import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

  Page<TaskView> getTasks(TaskFilter filterCriteria , Pageable pageable);

  TaskView getTask(Long id);

  TaskView createTask(TaskPostRequest dto, Long projectId);

  void updateTask(Long id, TaskPutRequest dto);

  void deleteTask(Long id);
}
