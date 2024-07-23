package com.todo.todo.repository.task;

import com.todo.todo.model.entity.ProjectEntity;
import com.todo.todo.model.entity.TaskEntity;
import com.todo.todo.model.views.Task;
import com.todo.todo.repository.project.ProjectRepositoryJpa;
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
  private final ProjectRepositoryJpa projectRepositoryJpa;

  @Override
  public Page<Task> findAllByTaskIdAndUserEmail(Long taskId, String userEmail, Pageable pageable) {
    return taskRepositoryJpa.findAllByProject_IdAndProject_User_Email(taskId, userEmail, pageable).map(TaskMapper::toTask);
  }

  @Override
  public Optional<Task> findByIdAndUserEmail(Long id, String userEmail) {
    return taskRepositoryJpa.findByIdAndProject_User_Email(id, userEmail).map(TaskMapper::toTask);
  }

  @Override
  public void update(Task task) {
    save(task);
  }

  @Override
  public void save(Task task) {
    TaskEntity taskEntity = getTaskEntity(task);

    taskRepositoryJpa.save(taskEntity);
  }

  @Override
  public void delete(Task task) {
    TaskEntity taskEntity = getTaskEntity(task);

    taskRepositoryJpa.delete(taskEntity);
  }

  private TaskEntity getTaskEntity(Task task) {
    ProjectEntity projectEntity = projectRepositoryJpa.findById(task.projectId())
        .orElseThrow();

    return TaskMapper.toTaskEntity(task, projectEntity);
  }

}
