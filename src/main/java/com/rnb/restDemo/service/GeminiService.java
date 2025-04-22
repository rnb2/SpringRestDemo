package com.rnb.restDemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    /**
     * Prefer to use this ChatClient.Builder when you use one AI model
     */
    public GeminiService(@Qualifier(value = "geminiChatClientBuilder") ChatClient.Builder builder) {
        //this.chatClient = builder.build();
        this.chatClient = builder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }

    /**
     * Use concrete chat model when you are using more than one AI model
     */
    /*public GeminiService(VertexAiGeminiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }*/

    public ResponseEntity<String> chat(String message) {
        ChatResponse chatResponse = this.chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        if (chatResponse != null) {
            System.out.println("com.rnb.restDemo.service.GeminiService.chat.model: "
                    + chatResponse.getMetadata());

            return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
        }
        return ResponseEntity.notFound().build();
    }
}
