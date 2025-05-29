package com.zsanjay.chatbot.repository;

import com.zsanjay.chatbot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
