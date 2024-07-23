package com.todo.todo.repository.task;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.entity.TaskEntity;
import com.todo.todo.model.views.Task;
import com.todo.todo.utils.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImp implements TaskRepository {

  private final TaskRepositoryJpa taskRepositoryJpa;

  @Override
  public Page<Task> findAllByTaskId(Long taskId, Pageable pageable) {
    return taskRepositoryJpa.findAllByProject_Id(taskId, pageable).map(TaskMapper::toTask);
  }

  @Override
  public Optional<Task> findByIdAndUserEmail(Long id, String userEmail) {
    return taskRepositoryJpa.findByIdAndProject_User_Email(id, userEmail).map(TaskMapper::toTask);
  }

  @Override
  public Task update(Task task) {
    TaskEntity entity = taskRepositoryJpa.findById(task.id())
        .orElseThrow(() -> new BadResponseException("Task not found"));

    entity.setName(task.name());
    entity.setDescription(task.description());
    entity.setDone(task.done());

    return TaskMapper.toTask(taskRepositoryJpa.save(entity));
  }

  @Override
  public Task save(Task task , Long projectId) {
    TaskEntity entity = new TaskEntity();

    entity.setName(task.name());
    entity.setDescription(task.description());
    entity.setDone(task.done());

    return TaskMapper.toTask(taskRepositoryJpa.save(entity));
  }

  @Override
  public void delete(Task task) {
    taskRepositoryJpa.delete(TaskMapper.toTaskEntity(task));
  }


}
