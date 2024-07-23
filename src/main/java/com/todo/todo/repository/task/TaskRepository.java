package com.todo.todo.repository.task;

import com.todo.todo.model.views.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskRepository {

  Page<Task> findAllByTaskId(Long taskId , Pageable pageable);
  Optional<Task> findByIdAndUserEmail(Long id , String userEmail);
  Task update(Task task);
  Task save(Task task , Long projectId);
  void delete(Task task);
}
