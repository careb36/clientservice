package com.coderhouse.clientservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name="clients")
public class Client {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;
    private LocalDateTime birthDate;

    // Constructor
    public Client(int id, String name, String lastName, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Client() {

    }
}
