package com.zsanjay.chatbot.controller;

import com.zsanjay.chatbot.model.ChatRequest;
import com.zsanjay.chatbot.service.ChatBotService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatBotController {
    private final ChatBotService chatBotService;

    Logger logger = LoggerFactory.getLogger(ChatBotController.class);

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest chatRequest) {
        String chatResponse = chatBotService.processChat(chatRequest.message());
        logger.info("response = " + chatResponse);
        return ResponseEntity.ok(chatResponse);
    }

}
