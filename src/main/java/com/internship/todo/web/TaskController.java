package com.internship.todo.web;

import com.internship.todo.feature.task.dto.TaskPageableRequest;
import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskService service;

  @GetMapping
  public ResponseEntity<TaskView> getTask(@RequestParam Long id) {
    return ResponseEntity.ok(service.getTask(id));
  }

  @PostMapping("/page")
  public ResponseEntity<Page<TaskView>> getTasks(
      @RequestParam Long projectId,
      @Valid @RequestBody TaskPageableRequest pageRequest
  ) {
    return ResponseEntity.ok(service.getTasks(projectId, pageRequest));
  }

  @PostMapping
  public ResponseEntity<TaskView> createTask(@RequestParam Long projectId, @Valid @RequestBody TaskPostRequest task) {
    return new ResponseEntity<>(service.createTask(task, projectId), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateTask(@PathVariable Long id, @Valid @RequestBody TaskPutRequest task) {
    service.updateTask(id, task);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    service.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
