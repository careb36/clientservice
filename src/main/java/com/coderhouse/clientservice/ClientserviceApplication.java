package com.coderhouse.clientservice;

import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ClientserviceApplication implements CommandLineRunner {

	@Autowired
	ClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(ClientserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDate birthDate = LocalDate.of(1983,10,29);
		System.out.println(clientService.calcEdge(birthDate));

	}
}
