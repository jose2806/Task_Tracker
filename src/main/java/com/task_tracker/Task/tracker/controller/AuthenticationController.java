package com.task_tracker.Task.tracker.controller;

import com.task_tracker.Task.tracker.domain.User.User;
import com.task_tracker.Task.tracker.domain.User.UserAuthenticationData;
import com.task_tracker.Task.tracker.infra.security.JWTTokenData;
import com.task_tracker.Task.tracker.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuth(@RequestBody @Valid UserAuthenticationData data) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var userAuth = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.getToken((User) userAuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
