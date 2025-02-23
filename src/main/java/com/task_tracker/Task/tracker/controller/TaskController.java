package com.task_tracker.Task.tracker.controller;

import com.task_tracker.Task.tracker.domain.Task;
import com.task_tracker.Task.tracker.domain.TaskRegistrationData;
import com.task_tracker.Task.tracker.domain.TaskRepository;
import com.task_tracker.Task.tracker.domain.TaskResponseData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity addTask(@RequestBody @Valid TaskRegistrationData data, UriComponentsBuilder uriComponentsBuilder){
        Task task = taskRepository.save(new Task(data));
        TaskResponseData response = new TaskResponseData(task.getId(), task.getDescription(),
                task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
        URI url = uriComponentsBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }
}
