package com.coderhouse.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The main application class for the Client Service application.
 * This class serves as the entry point for the Spring Boot application.
 * It is annotated with @SpringBootApplication, indicating that it is a Spring Boot application.
 */
@SpringBootApplication
public class ClientserviceApplication {

	/**
	 * The main method that serves as the entry point of the application.
	 * This method will launch the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 * @author Carolina Pereira
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClientserviceApplication.class, args);
	}

	/**
	 * Bean creation method for RestTemplate.
	 * RestTemplate is used for making HTTP requests to external services.
	 * This method creates and configures a RestTemplate bean to be used across the application.
	 *
	 * @return A new instance of RestTemplate.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
