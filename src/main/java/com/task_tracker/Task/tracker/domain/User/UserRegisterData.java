package com.task_tracker.Task.tracker.domain.User;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterData(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
