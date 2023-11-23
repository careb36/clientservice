package com.coderhouse.clientservice;

import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientserviceApplication{

	@Autowired
	ClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(ClientserviceApplication.class, args);
	}

}
