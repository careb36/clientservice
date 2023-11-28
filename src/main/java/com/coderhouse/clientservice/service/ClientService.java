package com.coderhouse.clientservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service class for handling client-related operations.
 * This class provides functionalities to create and find clients, along with calculating their age.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO create(Client client) {
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        return convertToDTO(client);
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        dto.setBirthDate(client.getBirthDate());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setTelephone(client.getTelephone());
        return dto;
    }
}
