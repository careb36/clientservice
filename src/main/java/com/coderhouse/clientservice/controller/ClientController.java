package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * REST controller for managing client-related operations.
 * This controller handles HTTP requests for operations on the Client entity,
 * including creation, retrieval, updating, and deletion of clients.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Autowired constructor for dependency injection of ClientService.
     * @param clientService The service layer dependency for client operations.
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Endpoint for creating a new client.
     * Accepts a client object in the request body, validates it, and saves it using the client service.
     *
     * @param client The client object to be created, validated by @Valid annotation.
     * @return ResponseEntity containing the created ClientDTO and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody Client client) {
        ClientDTO newClient = clientService.create(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving a client by ID.
     * Fetches client details from the client service based on the provided ID.
     *
     * @param id The ID of the client to be retrieved.
     * @return ResponseEntity containing the ClientDTO and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Endpoint for updating an existing client.
     * Updates client details based on the provided ID and request body.
     *
     * @param id            The ID of the client to be updated.
     * @param clientDetails The client object containing updated fields, validated by @Valid annotation.
     * @return ResponseEntity containing the updated ClientDTO and HTTP status OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        ClientDTO updatedClient = clientService.update(id, clientDetails);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a client by ID.
     * Removes the client associated with the provided ID using the client service.
     *
     * @param id The ID of the client to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}