package com.rnb.restDemo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * For understanding of working autoconfiguration
 * This class smth like JpaRepositoriesAutoConfiguration
 */
@Conditional({JpaCondition.class})
@Configuration
public class JpaConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("JpaConfiguration init");
    }
}
