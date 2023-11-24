package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * Service class for handling client-related operations.
 * This class provides functionalities to create and find clients, along with calculating their age.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Creates a new client in the repository.
     * After saving the client, it calculates the client's age and prints the client's details as JSON.
     *
     * @param c The client to be created.
     * @return The saved client entity.
     */
    public Client create(Client c) {
        Client savedClient = this.clientRepository.save(c);
        int age = calculateAge(savedClient.getBirthDate());

        // Manually constructing a JSON string for demonstration
        String jsonOutput = "{\n" +
                "   \"id\": \"" + savedClient.getId() + "\",\n" +
                "   \"name\": \"" + savedClient.getName() + "\",\n" +
                "   \"lastName\": \"" + savedClient.getLastName() + "\",\n" +
                "   \"birthDate\": \"" + savedClient.getBirthDate() + "\",\n" +
                "   \"age\": " + age + "\n" + // Adding the age
                "}";
        System.out.println("Saved client: " + jsonOutput);

        return savedClient;
    }

    @Autowired
    private ObjectMapper objectMapper; // Injects ObjectMapper for JSON processing

    /**
     * Finds a client by ID.
     * If found, it serializes the client to JSON, including the calculated age.
     *
     * @param id The ID of the client to find.
     * @return A JSON string representing the client or an error message if not found.
     */
    public String findById(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    ObjectNode clientJson = objectMapper.valueToTree(client);
                    clientJson.put("age", calculateAge(client.getBirthDate()));
                    try {
                        return objectMapper.writeValueAsString(clientJson);
                    } catch (Exception e) {
                        throw new RuntimeException("Error serializing to JSON", e);
                    }
                })
                .orElse("{\"error\":\"Client not found with ID: " + id + "\"}");
    }

    /**
     * Calculates the age of a person based on their birth date.
     *
     * @param birthDate The birth date of the person.
     * @return The calculated age in years.
     */
    public int calculateAge(LocalDate birthDate) {
        LocalDate birthLocalDate = LocalDate.parse(birthDate.toString());
        return Period.between(birthLocalDate, LocalDate.now()).getYears();
    }
}
