package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.exception.ClientNotFoundException;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing clients.
 * This class provides functionality to create, retrieve, update, and delete client data,
 * converting between DTOs and entity models as needed.
 * It interacts with ClientRepository for database operations.
 * <p>
 * @author: Carolina Pereira
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new client in the system.
     * Converts the provided ClientDTO to a Client entity and saves it to the database.
     * Returns the saved entity as a DTO.
     *
     * @param clientDTO Client data transfer object.
     * @return The created client as a DTO.
     */
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        if (clientDTO.getBirthDate() != null) {
            client.setBirthDate(clientDTO.getBirthDate().atStartOfDay());
        }
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setTelephone(clientDTO.getTelephone());
        return client;
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));
        return convertToDTO(client);
    }

    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));
        Client clientToUpdate = convertToEntity(clientDTO);
        clientToUpdate.setId(existingClient.getId()); // Ensure the ID is set
        Client updatedClient = clientRepository.save(clientToUpdate);
        return convertToDTO(updatedClient);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    /**
     * Converts a Client entity to a ClientDTO.
     * This method is used to transfer client data between layers without exposing the entity.
     *
     * @param client The Client entity to convert.
     * @return The converted Client as a DTO.
     */
    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        if (client.getBirthDate() != null) {
            dto.setBirthDate(client.getBirthDate().toLocalDate());
        }
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setTelephone(client.getTelephone());
        return dto;
    }
}
