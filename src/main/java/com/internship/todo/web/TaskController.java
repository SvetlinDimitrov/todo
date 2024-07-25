package com.internship.todo.web;

import com.internship.todo.feature.task.dto.TaskFilter;
import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskPutRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskService service;

  @GetMapping("/{id}")
  public ResponseEntity<TaskView> getTask(@PathVariable Long id) {
    return ResponseEntity.ok(service.getTask(id));
  }

  @GetMapping
  public ResponseEntity<Page<TaskView>> getTasks(
      Pageable pageable,
      @RequestParam(required = false) Long projectId,
      @RequestParam(required = false) String filterByName) {
    TaskFilter filterCriteria = new TaskFilter(projectId, filterByName);
    return ResponseEntity.ok(service.getTasks(filterCriteria, pageable));
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
