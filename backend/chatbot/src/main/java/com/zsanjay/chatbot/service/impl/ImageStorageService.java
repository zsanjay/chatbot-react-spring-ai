package com.zsanjay.chatbot.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.zsanjay.chatbot.entity.User;
import com.zsanjay.chatbot.exception.UserNotFoundException;
import com.zsanjay.chatbot.repository.UserRepository;
import com.zsanjay.chatbot.service.StorageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageStorageService implements StorageService {
    private final UserRepository userRepository;

    public ImageStorageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public String upload(Long userId, String image) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User doesn't exists with the userId " + userId));
        user.setAvatarImage(image);
        user.setAvatarImageSet(true);
        userRepository.save(user);
        return user.getAvatarImage();
    }
}
