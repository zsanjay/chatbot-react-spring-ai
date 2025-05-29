package com.zsanjay.chatbot.model;

import jakarta.validation.constraints.NotBlank;

public record ChatBotUser(@NotBlank(message = "username is empty") String username, @NotBlank(message = "email is empty") String email,
                          @NotBlank(message = "Password is empty") String password) {
}
