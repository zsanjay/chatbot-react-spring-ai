package com.zsanjay.chatbot.entity;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "chatbot_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "chat_messages", joinColumns = @JoinColumn(name = "user_id"))
    private List<Chat> messages;
    @Lob
    @Column(name = "avatar_image", columnDefinition = "TEXT")
    private String avatarImage;
    private boolean isAvatarImageSet;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public boolean isAvatarImageSet() {
        return isAvatarImageSet;
    }

    public void setAvatarImageSet(boolean avatarImageSet) {
        isAvatarImageSet = avatarImageSet;
    }

    public List<Chat> getMessages() {
        return messages;
    }
    public void setMessages(List<Chat> messages) {
        this.messages = messages;
    }
}
