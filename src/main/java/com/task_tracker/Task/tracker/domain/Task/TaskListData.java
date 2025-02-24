package com.task_tracker.Task.tracker.domain.Task;

import java.time.LocalDateTime;

public record TaskListData(
        Long id,
        String description,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public TaskListData(Task task){
        this(task.getId(), task.getDescription(), task.getStatus(),
                task.getCreatedAt(), task.getUpdatedAt());
    }
}
