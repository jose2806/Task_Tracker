package com.task_tracker.Task.tracker.controller;

import com.task_tracker.Task.tracker.domain.Task.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<TaskListData>> listTasks(@PageableDefault(size=3) Pageable pagination){
        return ResponseEntity.ok(taskRepository.findAll(pagination).map(TaskListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTask(@RequestBody @Valid TaskUpdateData data){
        Task task = taskRepository.getReferenceById(data.id());
        task.updateData(data);
        return ResponseEntity.ok(new TaskResponseData(task.getId(), task.getDescription(),
                task.getStatus(), task.getCreatedAt(), task.getUpdatedAt()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTask(@PathVariable Long id){
        Task task = taskRepository.getReferenceById(id);
        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }
}
