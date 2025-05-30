package com.zsanjay.chatbot.service.impl;

import com.zsanjay.chatbot.entity.Chat;
import com.zsanjay.chatbot.entity.User;
import com.zsanjay.chatbot.exception.UserNotFoundException;
import com.zsanjay.chatbot.repository.UserRepository;
import com.zsanjay.chatbot.service.AiService;
import com.zsanjay.chatbot.service.ChatBotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final AiService aiService;
    private final UserRepository userRepository;
    public ChatBotServiceImpl(AiService aiService, UserRepository userRepository) {

        this.aiService = aiService;
        this.userRepository = userRepository;
    }
    @Override
    public String processChat(String chatRequest, Long userId) {
        String response = aiService.chat(chatRequest);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist with the userId " + userId));
        user.getMessages().add(new Chat(chatRequest, response));
        userRepository.save(user);
        return response;
    }

    @Transactional
    @Override
    public List<Chat> getAllChats(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist with the userId " + userId));
        return user.getMessages();
    }
}
