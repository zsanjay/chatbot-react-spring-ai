package com.zsanjay.chatbot.service.impl;

import com.zsanjay.chatbot.service.AiService;
import com.zsanjay.chatbot.service.ChatBotService;
import org.springframework.stereotype.Service;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final AiService aiService;
    public ChatBotServiceImpl(AiService aiService) {
        this.aiService = aiService;
    }
    @Override
    public String processChat(String chatRequest) {
        return aiService.chat(chatRequest);
    }
}
