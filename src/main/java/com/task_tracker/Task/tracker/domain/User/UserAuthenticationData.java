package com.task_tracker.Task.tracker.domain.User;

public record UserAuthenticationData(
        String email,
        String password
) {
}
