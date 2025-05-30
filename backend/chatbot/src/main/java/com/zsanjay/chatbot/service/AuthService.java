package com.zsanjay.chatbot.service;

import com.zsanjay.chatbot.exception.UserAlreadyExistsException;
import com.zsanjay.chatbot.exception.UserNotFoundException;
import com.zsanjay.chatbot.model.ChatBotLoginRequest;
import com.zsanjay.chatbot.model.ChatBotUser;

public interface AuthService {
    ChatBotUser register(ChatBotUser chatBotUser) throws UserAlreadyExistsException;
    ChatBotUser login(ChatBotLoginRequest chatBotLoginRequest) throws UserNotFoundException;
}
