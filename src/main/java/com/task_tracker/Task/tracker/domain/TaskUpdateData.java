package com.task_tracker.Task.tracker.domain;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskUpdateData(
        @NotNull
        Long id,
        String description,
        String status
) {
}
