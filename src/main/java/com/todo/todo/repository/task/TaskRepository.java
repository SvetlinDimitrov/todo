package com.todo.todo.repository.task;

import com.todo.todo.model.views.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskRepository {

  Page<Task> findAllByTaskIdAndUserEmail(Long taskId, String email, Pageable pageable);

  Optional<Task> findByIdAndUserEmail(Long id , String userEmail);

  void update(Task task);

  void save(Task task);

  void delete(Task task);
}
