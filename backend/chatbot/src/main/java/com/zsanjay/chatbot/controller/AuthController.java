package com.zsanjay.chatbot.controller;

import com.zsanjay.chatbot.model.ChatBotLoginRequest;
import com.zsanjay.chatbot.model.ChatBotUser;
import com.zsanjay.chatbot.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    Logger log = LoggerFactory.getLogger(AuthController.class);
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid ChatBotUser chatBotUser) {
        try {
            Long userId =  this.authService.register(chatBotUser);
            return ResponseEntity.ok("User is successfully registered with the " + userId);
        } catch(Exception exception) {
            log.error("Something went wrong " + exception.getMessage());
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ChatBotLoginRequest chatBotLoginRequest) {
        try {
            ChatBotUser response =  this.authService.login(chatBotLoginRequest);
            return ResponseEntity.ok(response);
        } catch(Exception exception) {
            log.error("Something went wrong " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }
}
