package com.todo.todo.controller;

import com.todo.todo.model.dto.CreateProject;
import com.todo.todo.model.dto.UpdateProject;
import com.todo.todo.model.views.Project;
import com.todo.todo.service.ProjectServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

  private final ProjectServiceImp service;

  @GetMapping
  public ResponseEntity<Page<Project>> getProjects(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "15") int size
  ) {
    return ResponseEntity.ok(service.getProjects(page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Project> getProject(@PathVariable Long id) {
    return ResponseEntity.ok(service.getProject(id));
  }

  @PostMapping
  public ResponseEntity<?> createProject(@RequestBody CreateProject task) {
    service.createProject(task);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody UpdateProject task) {
    service.updateProject(id, task);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProject(@PathVariable Long id) {
    service.deleteProject(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
