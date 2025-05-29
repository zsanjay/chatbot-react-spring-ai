package com.zsanjay.chatbot.model;

import jakarta.validation.constraints.NotBlank;

public record ChatBotLoginRequest(@NotBlank String username , @NotBlank String password) {
}
