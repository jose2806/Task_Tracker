package com.task_tracker.Task.tracker.domain.Task;

import jakarta.validation.constraints.NotBlank;

public record TaskRegistrationData (
    @NotBlank
    String description

    ){}
