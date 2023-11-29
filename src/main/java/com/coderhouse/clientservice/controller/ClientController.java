package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.ClientDTO;
import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody Client client) {
        ClientDTO newClient = clientService.create(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        ClientDTO updatedClient = clientService.update(id, clientDetails);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

