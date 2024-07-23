package com.todo.todo.controller;

import com.todo.todo.model.dto.CreateTask;
import com.todo.todo.model.dto.UpdateTask;
import com.todo.todo.model.views.Task;
import com.todo.todo.service.TaskServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

  private final TaskServiceImp service;

  @GetMapping
  public Page<Task> getTasks(
      @RequestParam Long projectId,
      @RequestParam(value = "0") int page,
      @RequestParam(value = "15") int size
  ) {
    return service.getTasks(projectId, page, size);
  }

  @GetMapping("/{id}")
  public Task getTask(@PathVariable Long id) {
    return service.getTask(id);
  }

  @PostMapping
  public void createTask(@RequestBody CreateTask task, @RequestParam Long projectId) {
    service.createTask(task, projectId);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @RequestBody UpdateTask task) {
    return service.updateTask(id, task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    service.deleteTask(id);
  }
}
