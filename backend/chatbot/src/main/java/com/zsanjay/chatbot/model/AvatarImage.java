package com.zsanjay.chatbot.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record AvatarImage(@NotBlank String image) {
}
