package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.model.Client;
import com.coderhouse.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client create(Client c) {
        return this.clientRepository.save(c);
    }

    public String findById(Long id) {
        return clientRepository.findById(id)
                .map(c -> c.toString() + " Edad: " + calculateAge(c.getBirthDate()))
                .orElse("Cliente no encontrado con ID: " + id);
    }

    public int calculateAge(LocalDate birthDate) {
        LocalDate birthLocalDate = LocalDate.parse(birthDate.toString());
        return Period.between(birthLocalDate, LocalDate.now()).getYears();
    }
}
