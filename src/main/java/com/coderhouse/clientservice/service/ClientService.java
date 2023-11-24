package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client create(Client c) {
        return this.clientRepository.save(c);
    }
    @Autowired
    private ObjectMapper objectMapper; // Inyecta ObjectMapper

    public String findById(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    ObjectNode clientJson = objectMapper.valueToTree(client);
                    clientJson.put("edad", calculateAge(client.getBirthDate()));
                    try {
                        return objectMapper.writeValueAsString(clientJson);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al serializar a JSON", e);
                    }
                })
                .orElse("{\"error\":\"Cliente no encontrado con ID: " + id + "\"}");
    }

    public int calculateAge(LocalDate birthDate) {
        LocalDate birthLocalDate = LocalDate.parse(birthDate.toString());
        return Period.between(birthLocalDate, LocalDate.now()).getYears();
    }
}
