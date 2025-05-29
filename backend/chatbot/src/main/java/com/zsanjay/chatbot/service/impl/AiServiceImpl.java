package com.zsanjay.chatbot.service.impl;

import com.zsanjay.chatbot.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {
    private final ChatClient chatClient;
    public AiServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String prompt) {
        return chatClient
                .prompt(prompt)
                .call().content();
    }

}
