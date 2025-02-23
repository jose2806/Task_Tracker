package com.task_tracker.Task.tracker.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRegistrationData (
    @NotBlank
    String descripcion,
    @NotBlank
    String status,
    @NotNull
    LocalDateTime createdAt,
    @NotNull
    LocalDateTime updatedAt
    ){}
