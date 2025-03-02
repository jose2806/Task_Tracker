package com.task_tracker.Task.tracker.domain.Task;

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
        this.description = data.description();
        this.status = "TODO";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateData(@Valid TaskUpdateData data) {
        if(data.description() != null){
            this.description = data.description();
            this.updatedAt = LocalDateTime.now();
        }
        if (data.status() != null){
            this.status = data.status();
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void updateData(@Valid TaskUpdateDataById data) {
        if(data.description() != null){
            this.description = data.description();
            this.updatedAt = LocalDateTime.now();
        }
        if (data.status() != null){
            this.status = data.status();
            this.updatedAt = LocalDateTime.now();
        }
    }


}
