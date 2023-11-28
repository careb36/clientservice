package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coderhouse.clientservice.dto.ClientDTO;

/**
 * REST Controller for managing client-related operations.
 * This controller provides endpoints for creating a new client and retrieving client details.
 */
@RestController
@RequestMapping(path = "api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<ClientDTO> create(@RequestBody Client client) {
        ClientDTO createdClientDTO = clientService.create(client);
        return new ResponseEntity<>(createdClientDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving a client by ID.
     * Fetches client details from the client service. If the client is found,
     * returns the client details as a JSON string along with HTTP status OK.
     *
     * @param id The ID of the client to be retrieved.
     * @return ResponseEntity with client details as JSON string and HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findById(id);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}
