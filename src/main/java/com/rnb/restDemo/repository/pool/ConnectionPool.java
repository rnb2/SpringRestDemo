package com.rnb.restDemo.repository.pool;

import jakarta.annotation.PostConstruct;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ToString
public class ConnectionPool {
    private final String userName;
    private final String password;
    private final Integer poolSize;
    private final String url;

    public ConnectionPool(@Value("${spring.datasource.username}") String userName,
                          @Value("${spring.datasource.password}") String password,
                          @Value("${db.pool.size}") Integer poolSize,
                          @Value("${spring.datasource.url}") String url) {
        this.userName = userName;
        this.password = password;
        this.poolSize = poolSize;
        this.url = url;
    }

    @PostConstruct
    public void initialize() {
            log.info("Initializing connection pool");
    }
}
