package com.todo.todo.service;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.CreateTask;
import com.todo.todo.model.dto.UpdateTask;
import com.todo.todo.model.views.Task;
import com.todo.todo.repository.project.ProjectRepository;
import com.todo.todo.repository.task.TaskRepository;
import com.todo.todo.utils.mappers.TaskMapper;
import com.todo.todo.utils.validator.CreateTaskValidator;
import com.todo.todo.utils.validator.PageableValidator;
import com.todo.todo.utils.validator.UpdateTaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImp {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;

  public Page<Task> getTasks(Long projectId, int page, int size) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    PageableValidator.validate(page , size);

    Pageable pageable = PageRequest.of(page, size);

    return taskRepository.findAllByTaskIdAndUserEmail(projectId, userEmail, pageable);
  }

  public Task getTask(Long id) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return taskRepository.findByIdAndUserEmail(id , userEmail)
        .orElseThrow(() -> new BadResponseException("Task not found"));
  }

  public void createTask(CreateTask dto, Long projectId) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    if (projectId < 0) throw new BadResponseException("Invalid project id");

    projectRepository.findByIdAndUserEmail(projectId, userEmail)
        .orElseThrow(() -> new BadResponseException("Project not found"));

    CreateTaskValidator.validate(dto);

    taskRepository.save(TaskMapper.toTask(dto, projectId));
  }

  public void updateTask(Long id, UpdateTask dto) {
    UpdateTaskValidator.validate(dto);

    Task task = getTask(id);
    Task taskToUpdate = new Task(task.id(), dto.name(), dto.description(), dto.done() , task.projectId());

    taskRepository.update(taskToUpdate);
  }

  public void deleteTask(Long id) {
    taskRepository.delete(getTask(id));
  }
}
