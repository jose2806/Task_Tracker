package com.task_tracker.Task.tracker.domain.Task;

import jakarta.validation.constraints.NotNull;

public record TaskUpdateData(
        @NotNull
        Long id,
        String description,
        String status
) {
}
