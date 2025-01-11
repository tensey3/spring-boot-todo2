package com.example.todo.controller.api.projects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectCreateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectCreateController {

  private final ProjectCreateService projectCreateService;

  public ProjectCreateController(ProjectCreateService projectCreateService) {
    this.projectCreateService = projectCreateService;
  }

  @PostMapping
  public ResponseEntity<Project> invoke(@Valid @RequestBody ProjectCreateRequest request) {
    Project project = this.projectCreateService.invoke(request);
    return ResponseEntity.ok(project);
  }
}
