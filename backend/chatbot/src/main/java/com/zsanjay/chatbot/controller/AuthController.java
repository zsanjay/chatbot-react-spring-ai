package com.zsanjay.chatbot.controller;

import com.zsanjay.chatbot.model.ChatBotLoginRequest;
import com.zsanjay.chatbot.model.ChatBotUser;
import com.zsanjay.chatbot.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    Logger log = LoggerFactory.getLogger(AuthController.class);
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid ChatBotUser chatBotUser) {
        try {
            chatBotUser =  this.authService.register(chatBotUser);
            return ResponseEntity.ok(chatBotUser);
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

    @GetMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable("id") Long userId) {
        try {
            this.authService.logout(userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(Exception exception) {
            log.error("Something went wrong " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
