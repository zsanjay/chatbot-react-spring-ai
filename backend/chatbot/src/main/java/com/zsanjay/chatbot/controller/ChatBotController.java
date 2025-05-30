package com.zsanjay.chatbot.controller;

import com.zsanjay.chatbot.entity.Chat;
import com.zsanjay.chatbot.exception.ImageUploadException;
import com.zsanjay.chatbot.model.AvatarImage;
import com.zsanjay.chatbot.model.AvatarUploadResponse;
import com.zsanjay.chatbot.model.ChatRequest;
import com.zsanjay.chatbot.model.ChatResponse;
import com.zsanjay.chatbot.service.ChatBotService;
import com.zsanjay.chatbot.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatBotController {
    private final ChatBotService chatBotService;
    private final StorageService storageService;

    Logger logger = LoggerFactory.getLogger(ChatBotController.class);

    public ChatBotController(ChatBotService chatBotService, StorageService storageService) {
        this.chatBotService = chatBotService;
        this.storageService = storageService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {
        String chatResponse = chatBotService.processChat(chatRequest.message() , chatRequest.userId());
        logger.info("response = " + chatResponse);
        return ResponseEntity.ok(new ChatResponse(chatRequest.message(), chatResponse));
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<?> chat(@PathVariable("id") Long userId) {
        List<Chat> chats = chatBotService.getAllChats(userId);
        logger.info("response = " + chats);
        List<ChatResponse> chatResponseList = new ArrayList<>();
        chats.forEach((chat) -> chatResponseList.add(new ChatResponse(chat.getChatRequest() , chat.getChatResponse())));
        return ResponseEntity.ok(chatResponseList);
    }

    @PostMapping(value = "/avatar/{id}", produces = "application/json")
    public ResponseEntity<AvatarUploadResponse> upload(@PathVariable("id") Long userId, @RequestBody AvatarImage avatarImage) {
        String key = null;
        try {
            key = storageService.upload(userId, avatarImage.image());
        } catch (IOException e) {
            throw new ImageUploadException("Something went wrong while uploading avatar," + e.getMessage());
        }
        return new ResponseEntity<>(new AvatarUploadResponse(true , key), HttpStatus.OK);
    }


}
