package com.internship.todo.feature.task.service;

import com.internship.todo.feature.project.entity.Project;
import com.internship.todo.feature.project.repository.ProjectRepository;
import com.internship.todo.feature.task.dto.TaskFilter;
import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.entity.Task;
import com.internship.todo.feature.task.repository.TaskRepository;
import com.internship.todo.feature.task.specification.TaskSpecification;
import com.internship.todo.infrastructure.security.service.UserDetailsAuthImp;
import com.internship.todo.infrastructure.shared.enums.ExceptionMessages;
import com.internship.todo.infrastructure.shared.exceptions.ProjectNotFoundException;
import com.internship.todo.infrastructure.shared.exceptions.TaskNotFoundException;
import com.internship.todo.infrastructure.shared.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;
  private final TaskMapper taskMapper;

  public Page<TaskView> getTasks(TaskFilter filterCriteria, Pageable pageable) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    Specification<Task> specification = TaskSpecification.filterByCriteria(filterCriteria, userEmail);

    return taskRepository.findAll(specification, pageable)
        .map(taskMapper::toTask);
  }

  public TaskView getTask(Long id) {

    return taskMapper.toTask(getTaskEntityByIdAndUserEmail(id));
  }

  public TaskView createTask(TaskPostRequest dto, Long projectId) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    Project project = projectRepository.findByIdAndUser_Email(projectId, userEmail)
        .orElseThrow(() -> new ProjectNotFoundException(
            String.format(ExceptionMessages.PROJECT_NOT_FOUND.getMessage(), projectId, userEmail))
        );

    Task taskToSave = taskMapper.toTaskEntity(dto);
    taskToSave.setProject(project);
    taskToSave.setCreationDate(LocalDateTime.now());
    taskToSave.setUpdatedDate(LocalDateTime.now());
    taskToSave.setDone(dto.done() != null && dto.done());

    return taskMapper.toTask(taskRepository.save(taskToSave));
  }

  public void updateTask(Long id, TaskPutRequest dto) {

    Task taskToUpdateAndSave = getTaskEntityByIdAndUserEmail(id);
    taskToUpdateAndSave.setName(dto.name());
    taskToUpdateAndSave.setDescription(dto.description());
    taskToUpdateAndSave.setDone(dto.done());
    taskToUpdateAndSave.setUpdatedDate(LocalDateTime.now());

    taskRepository.save(taskToUpdateAndSave);
  }

  public void deleteTask(Long id) {
    taskRepository.delete(getTaskEntityByIdAndUserEmail(id));
  }

  public Task getTaskEntityByIdAndUserEmail(Long id) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return taskRepository.findByIdAndProject_User_Email(id, userEmail)
        .orElseThrow(() -> new TaskNotFoundException(
            String.format(ExceptionMessages.TASK_NOT_FOUND.getMessage(), id, userEmail)
        ));
  }
}
