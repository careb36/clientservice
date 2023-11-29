package com.coderhouse.clientservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling client-related operations.
 * This class provides functionalities to create and find clients, along with calculating their age.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Método create existente...

    // Método para obtener todos los clientes
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para actualizar un cliente
    public ClientDTO update(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));

        client.setName(clientDetails.getName());
        client.setLastName(clientDetails.getLastName());
        client.setBirthDate(clientDetails.getBirthDate());
        client.setEmail(clientDetails.getEmail());
        client.setAddress(clientDetails.getAddress());
        client.setTelephone(clientDetails.getTelephone());

        Client updatedClient = clientRepository.save(client);
        return convertToDTO(updatedClient);
    }


    // Método para eliminar un cliente
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        dto.setBirthDate(LocalDate.from(client.getBirthDate()));
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setTelephone(client.getTelephone());
        return dto;
    }

    public ClientDTO create(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }


    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        return convertToDTO(client);
    }

}
