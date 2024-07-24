package com.internship.todo.web;

import com.internship.todo.feature.project.dto.ProjectPageableRequest;
import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

  private final ProjectService service;

  @GetMapping("/{id}")
  public ResponseEntity<ProjectView> getProject(@PathVariable Long id) {
    return ResponseEntity.ok(service.getProject(id));
  }

  @PostMapping("/page")
  public ResponseEntity<Page<ProjectView>> getProjects(@Valid @RequestBody ProjectPageableRequest pageRequest) {
    return ResponseEntity.ok(service.getProjects(pageRequest));
  }

  @PostMapping
  public ResponseEntity<?> createProject(@Valid @RequestBody ProjectPostPutRequest task) {
    service.createProject(task);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectPostPutRequest task) {
    service.updateProject(id, task);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProject(@PathVariable Long id) {
    service.deleteProject(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
