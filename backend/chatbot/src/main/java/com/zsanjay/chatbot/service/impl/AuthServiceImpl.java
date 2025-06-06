package com.zsanjay.chatbot.service.impl;

import com.zsanjay.chatbot.exception.UserAlreadyExistsException;
import com.zsanjay.chatbot.exception.UserNotFoundException;
import com.zsanjay.chatbot.model.ChatBotLoginRequest;
import com.zsanjay.chatbot.model.ChatBotUser;
import com.zsanjay.chatbot.repository.UserRepository;
import com.zsanjay.chatbot.service.AuthService;
import org.springframework.stereotype.Service;
import com.zsanjay.chatbot.entity.User;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public ChatBotUser register(ChatBotUser chatBotUser) throws UserAlreadyExistsException{
        User existingUser = userRepository.findByUsername(chatBotUser.username());

        if(Objects.nonNull(existingUser)) {
            throw new UserAlreadyExistsException("User with username="+ chatBotUser.username() +" already exists. Please try different username.");
        }

        User user = new User();
        user.setEmail(chatBotUser.email());
        user.setUsername(chatBotUser.username());
        user.setPassword(chatBotUser.password());

        user = userRepository.save(user);
        return new ChatBotUser(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(), user.isAvatarImageSet(), user.getAvatarImage());
    }

    @Override
    public ChatBotUser login(ChatBotLoginRequest chatBotLoginRequest) throws UserNotFoundException{

        User user = userRepository.findByUsername(chatBotLoginRequest.username());

        if(Objects.isNull(user)) {
            throw new UserNotFoundException("User doesn't exist with the username = " + chatBotLoginRequest.username());
        }

        if(user.getPassword().equals(chatBotLoginRequest.password())) {
            return new ChatBotUser(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(), user.isAvatarImageSet(), user.getAvatarImage());
        } else {
            throw new UserNotFoundException("Password is not correct. Please enter the correct password " + chatBotLoginRequest.username());
        }
    }

    @Override
    public void logout(Long userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User doesn't exist with the userId = " + userId));
    }
}
