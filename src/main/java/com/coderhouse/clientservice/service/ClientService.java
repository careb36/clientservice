package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public int calcEdge(LocalDate birthDate){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDate = LocalDate.parse(birthDate.toString(), fmt);

        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
