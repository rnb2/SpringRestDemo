package com.rnb.restDemo.config;

import com.rnb.restDemo.repository.pool.ConnectionPool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientBuilderConfigurer;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool(@Value("${spring.datasource.username}") String userName,
                                         @Value("${spring.datasource.password}") String password,
                                         @Value("${db.pool.size}") int poolSize,
                                         @Value("${spring.datasource.url}") String url) {
        return new ConnectionPool(userName, password, poolSize, url);
    }

    @Bean
    @Scope("prototype")
    ChatClient.Builder openAiChatClientBuilder(ChatClientBuilderConfigurer chatClientBuilderConfigurer,
                                               @Qualifier("openAiChatModel") ChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        return chatClientBuilderConfigurer.configure(builder);
    }

    @Bean
    @Scope("prototype")
    ChatClient.Builder geminiChatClientBuilder(ChatClientBuilderConfigurer chatClientBuilderConfigurer,
                                               @Qualifier("vertexAiGeminiChat") ChatModel chatModel) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);
        return chatClientBuilderConfigurer.configure(builder);
    }
}
