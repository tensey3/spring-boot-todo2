package com.example.todo.controller.api.projects;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectListService;

@RestController
@RequestMapping("/api/projects")
public class ProjectListController {

  private ProjectListService projectListServive;

  public ProjectListController(ProjectListService projectListServive) {
    this.projectListServive = projectListServive;
  }

  @GetMapping
  public ResponseEntity<List<Project>> invoke() {
    List<Project> projects = this.projectListServive.invoke();

    return ResponseEntity.ok(projects);
  }
}
