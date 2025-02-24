package com.task_tracker.Task.tracker.domain.User;

public record UserListData(
        Long id,
        String name,
        String email
) {
    public UserListData(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
