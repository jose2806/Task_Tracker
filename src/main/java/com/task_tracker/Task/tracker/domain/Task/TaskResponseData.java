package com.task_tracker.Task.tracker.domain.Task;

import java.time.LocalDateTime;

public record TaskResponseData(
        Long id,
        String description,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
