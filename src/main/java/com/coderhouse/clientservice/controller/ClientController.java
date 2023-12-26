package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * REST controller for managing {@link ClientDTO}.
 * This controller provides endpoints for CRUD operations on clients.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructs a ClientController with the given ClientService.
     * @param clientService The service to manage client data operations.
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Creates a new client.
     *
     * @param clientDTO The client data transfer object to create.
     * @return ResponseEntity with the created client and HTTP status.
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO newClient = clientService.create(clientDTO);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<ClientDTO>> createClients(@Valid @RequestBody List<ClientDTO> clientDTOs) {
        List<ClientDTO> newClients = clientService.createClients(clientDTOs);
        return new ResponseEntity<>(newClients, HttpStatus.CREATED);
    }


    /**
     * Retrieves a client by ID.
     *
     * @param id The ID of the client to retrieve.
     * @return ResponseEntity with the found client and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Retrieves all clients.
     *
     * @return ResponseEntity with the list of all clients and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Updates a client by ID.
     *
     * @param id        The ID of the client to update.
     * @param clientDTO The updated client data transfer object.
     * @return ResponseEntity with the updated client and HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.update(id, clientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    /**
     * Deletes a client by ID.
     *
     * @param id The ID of the client to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
