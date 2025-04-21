package com.rnb.restDemo.rest;

import com.rnb.restDemo.service.GeminiService;
import lombok.Getter;
//import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rnb/api/chat/gemini")
public class GeminiController {

    /*private final VertexAiGeminiChatModel chatModel;

    public GeminiController(VertexAiGeminiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/{message}")
    public String getAnswer(@PathVariable String message) {
        return chatModel.call(message);
    }*/

    private final GeminiService service;

    public GeminiController(GeminiService service) {
        this.service = service;
    }

    @PostMapping("/message")
    public String chat(@RequestBody String message) {
        /*String chat = this.service.chat(message);
        List<Generation> list = new ArrayList<>();
        AssistantMessage assistantMessage = new AssistantMessage(chat);
        Generation generation = new Generation(assistantMessage);
        list.add(generation);
        ChatResponse chatResponse = new ChatResponse(list);*/

        ResponseEntity<String> responseEntity = this.service.chat(message);
        System.out.println("Response: " + responseEntity.getBody());
        return responseEntity.getBody();
    }
}
