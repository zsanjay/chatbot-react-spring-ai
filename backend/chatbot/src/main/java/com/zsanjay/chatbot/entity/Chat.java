package com.zsanjay.chatbot.entity;

import jakarta.persistence.*;
@Embeddable
public class Chat {
    @Lob
    @Column(name = "message_request", columnDefinition = "TEXT")
    private String chatRequest;

    @Lob
    @Column(name = "message_response", columnDefinition = "TEXT")
    private String chatResponse;

    public Chat() {}
    public Chat(String chatRequest , String chatResponse) {
        this.chatRequest = chatRequest;
        this.chatResponse = chatResponse;
    }

    public String getChatRequest() {
        return chatRequest;
    }

    public String getChatResponse() {
        return chatResponse;
    }
}
