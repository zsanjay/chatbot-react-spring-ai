package com.zsanjay.chatbot.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.zsanjay.chatbot.entity.User;
import com.zsanjay.chatbot.exception.UserNotFoundException;
import com.zsanjay.chatbot.repository.UserRepository;
import com.zsanjay.chatbot.service.StorageService;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3StorageService implements StorageService {
    private static final String FILE_EXTENSION = "fileExtension";
    private final AmazonS3 amazonS3;
    private final UserRepository userRepository;
    private final String bucketName;

    public S3StorageService(AmazonS3 amazonS3 ,
                            @Value("${aws.s3.bucket-name}") String bucketName,
                            UserRepository userRepository
    ) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.userRepository = userRepository;

        //createBucketIfNotExists();
    }

    private void createBucketIfNotExists() {
        if(!amazonS3.doesBucketExist(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }

    @SneakyThrows
    @Override
    public String upload(Long userId, String image) throws IOException {

//        String key = UUID.randomUUID().toString();
        //amazonS3.putObject(bucketName, key, multipartFile.getInputStream(), extractObjectMetadata(multipartFile));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User doesn't exists with the userId " + userId));
        user.setAvatarImage(image);
        user.setAvatarImageSet(true);
        userRepository.save(user);
        return user.getAvatarImage();
    }

    private ObjectMetadata extractObjectMetadata(MultipartFile file) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.getUserMetadata().put(FILE_EXTENSION, FilenameUtils.getExtension(file.getOriginalFilename()));
        return objectMetadata;
    }
}
