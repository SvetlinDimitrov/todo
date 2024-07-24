package com.internship.todo.web;

import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

  private final TaskService service;

  @GetMapping
  public ResponseEntity<Page<TaskView>> getTasks(
      @RequestParam Long projectId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "15") int size
  ) {
    return ResponseEntity.ok(service.getTasks(projectId, page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskView> getTask(@PathVariable Long id) {
    return ResponseEntity.ok(service.getTask(id));
  }

  @PostMapping
  public ResponseEntity<?> createTask(@RequestBody TaskPostRequest task, @RequestParam Long projectId) {
    service.createTask(task, projectId);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskPutRequest task) {
    service.updateTask(id, task);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
    service.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
