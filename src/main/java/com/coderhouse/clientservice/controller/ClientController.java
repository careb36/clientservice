package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;
    @PostMapping("/")
        public ResponseEntity<Client> create (@RequestBody Client client){
            return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
        }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.clientService.findById(id), HttpStatus.OK);
    }
}