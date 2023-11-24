package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String result = clientService.findById(id);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}