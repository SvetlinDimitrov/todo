package com.internship.todo.web;

import com.internship.todo.feature.project.dto.ProjectPostRequest;
import com.internship.todo.feature.project.dto.ProjectPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.service.ProjectService;
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

  private final ProjectService service;

  @GetMapping
  public ResponseEntity<Page<ProjectView>> getProjects(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "15") int size
  ) {
    return ResponseEntity.ok(service.getProjects(page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectView> getProject(@PathVariable Long id) {
    return ResponseEntity.ok(service.getProject(id));
  }

  @PostMapping
  public ResponseEntity<?> createProject(@RequestBody ProjectPostRequest task) {
    service.createProject(task);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectPutRequest task) {
    service.updateProject(id, task);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProject(@PathVariable Long id) {
    service.deleteProject(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
