package com.rnb.restDemo;

import com.rnb.restDemo.repository.pool.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringProperties;

@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RestDemoApplication.class, args);

		/**
		 * com.rnb.restDemo.repository.pool.ConnectionPool#userName
		 * gets from application.properties or application-qa.properties
		 * This is depending on param arguments when run application: --spring.profiles.active=qa
		 */
		ConnectionPool connectionPool = applicationContext.getBean(ConnectionPool.class);
		System.out.println("ConnectionPool: " + connectionPool);
		System.out.println("'test.msg' value from spring.properties: " + SpringProperties.getProperty("test.msg"));
	}

}
