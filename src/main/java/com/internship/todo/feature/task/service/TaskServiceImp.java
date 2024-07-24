package com.internship.todo.feature.task.service;

import com.internship.todo.feature.project.entity.ProjectEntity;
import com.internship.todo.feature.project.repository.ProjectRepository;
import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.entity.TaskEntity;
import com.internship.todo.feature.task.repository.TaskRepository;
import com.internship.todo.feature.task.utils.mappers.TaskMapper;
import com.internship.todo.infrastructure.security.service.UserDetailsAuthImp;
import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.infrastructure.shared.validatiors.PageableValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;

  public Page<TaskView> getTasks(Long projectId, int page, int size) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    PageableValidator.validate(page , size);

    Pageable pageable = PageRequest.of(page, size);

    return taskRepository.findAllByProject_IdAndProject_User_Email(projectId, userEmail, pageable)
        .map(TaskMapper::toTask);
  }

  public TaskView getTask(Long id) {

    return TaskMapper.toTask(getTaskEntityByIdAndUserEmail(id));
  }

  public void createTask(TaskPostRequest dto, Long projectId) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    ProjectEntity project = projectRepository.findByIdAndUser_Email(projectId, userEmail)
        .orElseThrow(() -> new BadResponseException("Project not found"));

    TaskEntity taskToSave = TaskMapper.toTaskEntity(dto);
    taskToSave.setProject(project);

    taskRepository.save(taskToSave);
  }

  public void updateTask(Long id, TaskPutRequest dto) {

    TaskEntity taskToUpdateAndSave = getTaskEntityByIdAndUserEmail(id);
    taskToUpdateAndSave.setName(dto.name());
    taskToUpdateAndSave.setDescription(dto.description());
    taskToUpdateAndSave.setDone(dto.done());

    taskRepository.save(taskToUpdateAndSave);
  }

  public void deleteTask(Long id) {
    taskRepository.delete(getTaskEntityByIdAndUserEmail(id));
  }

  public TaskEntity getTaskEntityByIdAndUserEmail(Long id) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return taskRepository.findByIdAndProject_User_Email(id, userEmail)
        .orElseThrow(() -> new BadResponseException("Task not found"));
  }
}
