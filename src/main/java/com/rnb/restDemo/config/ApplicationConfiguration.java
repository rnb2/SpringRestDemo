package com.rnb.restDemo.config;

import com.rnb.restDemo.repository.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool(@Value("${spring.datasource.username}") String userName,
                                         @Value("${spring.datasource.password}") String password,
                                         @Value("${db.pool.size}") int poolSize,
                                         @Value("${spring.datasource.url}") String url) {
        return new ConnectionPool(userName, password, poolSize, url);
    }
}
