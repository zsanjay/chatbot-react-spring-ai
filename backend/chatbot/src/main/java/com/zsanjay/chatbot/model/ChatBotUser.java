package com.zsanjay.chatbot.model;

import jakarta.validation.constraints.NotBlank;

public record ChatBotUser(Long _id, @NotBlank(message = "username is empty") String username, @NotBlank(message = "email is empty") String email,
                          @NotBlank(message = "Password is empty") String password, boolean isAvatarImageSet, String avatarImage) {
}
