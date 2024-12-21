package com.example.todo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.PreUpdate;

@Data
@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String summary;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;


  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  private void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    this.setCreatedAt(now);
    this.setUpdatedAt(now);
  }

  @PreUpdate
  private void onUpdate() {
    this.setUpdatedAt(LocalDateTime.now());
  }
}
