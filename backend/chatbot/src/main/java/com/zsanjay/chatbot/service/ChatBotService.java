package com.zsanjay.chatbot.service;

import com.zsanjay.chatbot.entity.Chat;
import java.util.List;

public interface ChatBotService {
    String processChat(String chatRequest, Long userId);
    List<Chat> getAllChats(Long userId);
}
