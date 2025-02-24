package com.task_tracker.Task.tracker.controller;

import com.task_tracker.Task.tracker.domain.User.*;
import com.task_tracker.Task.tracker.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityConfigurations securityConfigurations;

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid UserRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        String encryptedPassword = securityConfigurations.passwordEncoder().encode(data.password());
        User user = new User(data.name(), data.email(), encryptedPassword);
        userRepository.save(user);
        UserResponseData response = new UserResponseData(user.getId(), user.getName(), user.getEmail());
        URI url = uriComponentsBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserListData>> usersList(){
        return ResponseEntity.ok(userRepository.findAll().stream().
                map(UserListData::new).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
