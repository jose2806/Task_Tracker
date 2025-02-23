package com.task_tracker.Task.tracker.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Task")
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(@Valid TaskRegistrationData data) {
        this.description = data.descripcion();
        this.status = data.status();
        this.createdAt = data.createdAt();
        this.updatedAt = data.updatedAt();
    }
}
