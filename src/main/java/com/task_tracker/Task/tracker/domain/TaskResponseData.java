package com.task_tracker.Task.tracker.domain;

import java.time.LocalDateTime;

public record TaskResponseData(
        Long id,
        String description,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
