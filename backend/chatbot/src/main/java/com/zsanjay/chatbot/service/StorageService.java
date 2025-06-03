package com.zsanjay.chatbot.service;

import com.zsanjay.chatbot.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String upload(Long userId, String image);
}
