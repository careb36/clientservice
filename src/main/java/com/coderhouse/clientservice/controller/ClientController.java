package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing client-related operations.
 * This controller provides endpoints for creating a new client and retrieving client details.
 */
@RequestMapping(path = "api/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Endpoint for creating a new client.
     * Accepts a client object in the request body and saves it using the client service.
     *
     * @param client Client object to be created.
     * @return ResponseEntity with created client and HTTP status code.
     */
    @PostMapping("/")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
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
    public ResponseEntity<String> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String result = clientService.findById(id);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
