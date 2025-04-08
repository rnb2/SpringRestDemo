package com.rnb.restDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringProperties;

@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
		System.out.println("'test.msg' value from spring.properties: " + SpringProperties.getProperty("test.msg"));
	}

}
