package com.zsanjay.chatbot.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String upload(Long userId, String image) throws IOException;
}
