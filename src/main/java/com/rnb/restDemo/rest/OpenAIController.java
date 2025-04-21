package com.rnb.restDemo.rest;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rnb/api/chat/openAI/")
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(@Qualifier("openAiChatModel") OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{message}")
    public String testOpenAI(@PathVariable String message) {

        String response = chatClient.prompt(message).call().content();

        // Configures using the `OPENAI_API_KEY`, `OPENAI_ORG_ID` and `OPENAI_PROJECT_ID`
        // environment variables
		/*OpenAIClient client = OpenAIOkHttpClient.fromEnv();

		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
				.addUserMessage("Say this is a test")
				.model(ChatModel.GPT_4)
				.build();
		ChatCompletion chatCompletion = client.chat().completions().create(params);
		System.out.println("ChatCompletion: " + chatCompletion);*/
        return response;
    }
}
