package com.syscom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationKafkaConsumerApp {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationKafkaConsumerApp.class, args);
	}
}
