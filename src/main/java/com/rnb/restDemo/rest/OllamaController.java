package com.rnb.restDemo.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rnb/api/chat/ollama")
public class OllamaController {

    private final ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
        System.out.println("OllamaController model:" + chatModel.getDefaultOptions().getModel());
    }

    @GetMapping("/message/{message}")
    public String generation(@PathVariable String message) {
        ResponseEntity<String> responseEntity = ResponseEntity.ok(this.chatClient
                .prompt(message)
                .call().content());
        return responseEntity.getBody();
    }

}
