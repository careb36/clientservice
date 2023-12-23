package com.coderhouse.clientservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing client-related operations in the online store.
 * This class encapsulates the business logic for handling clients, providing methods
 * for creating, retrieving, updating, and deleting client records.
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Constructor for dependency injection of ClientRepository.
     *
     * @param clientRepository The repository used for client operations.
     */
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new client record in the database.
     * Sets the creation timestamp and saves the client using the client repository.
     *
     * @param client The Client entity to be created.
     * @return The created ClientDTO.
     */
    public ClientDTO create(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    /**
     * Retrieves all client records from the database.
     * Converts each client entity to a ClientDTO.
     *
     * @return A list of ClientDTOs representing all clients.
     */
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing client record identified by ID.
     * Throws EntityNotFoundException if the client is not found.
     *
     * @param id            The ID of the client to be updated.
     * @param clientDetails The updated details of the client.
     * @return The updated ClientDTO.
     */
    public ClientDTO update(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));

        updateClientDetails(client, clientDetails);
        Client updatedClient = clientRepository.save(client);
        return convertToDTO(updatedClient);
    }

    /**
     * Deletes a client record identified by ID from the database.
     *
     * @param id The ID of the client to be deleted.
     */
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    /**
     * Retrieves a client record by its ID and converts it to a ClientDTO.
     * Throws EntityNotFoundException if the client is not found.
     *
     * @param id The ID of the client to find.
     * @return The ClientDTO representing the found client.
     */
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        return convertToDTO(client);
    }

    /**
     * Converts a Client entity to a ClientDTO.
     *
     * @param client The Client entity to convert.
     * @return The corresponding ClientDTO.
     */
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

    /**
     * Updates the fields of a Client entity with values from another Client object.
     *
     * @param client        The Client entity to be updated.
     * @param clientDetails The Client object containing updated values.
     */
    private void updateClientDetails(Client client, Client clientDetails) {
        client.setName(clientDetails.getName());
        client.setLastName(clientDetails.getLastName());
        client.setBirthDate(clientDetails.getBirthDate());
        client.setEmail(clientDetails.getEmail());
        client.setAddress(clientDetails.getAddress());
        client.setTelephone(clientDetails.getTelephone());
    }

}

