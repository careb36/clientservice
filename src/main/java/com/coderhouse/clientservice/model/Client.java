package com.coderhouse.clientservice.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Class representing a client in the system.
 */
@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the client

    @Column(name = "name")
    private String name; // Client's first name

    @Column(name = "lastName")
    private String lastName; // Client's last name

    @Column(name = "birthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // Client's date of birth, formatted as yyyy-MM-dd

    // Constructor with parameters
    public Client(int id, String name, String lastName, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Default constructor
    public Client() {

    }
}
