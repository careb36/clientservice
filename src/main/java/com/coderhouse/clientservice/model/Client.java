package com.coderhouse.clientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class representing a client in the system.
 */
@Data
@Entity
@Table(name = "client") // Ensure table name matches your database schema
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the client

    @Column(name = "name")
    private String name; // Client's first name

    @Column(name = "last_name")
    private String lastName; // Client's last name

    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // Client's date of birth, formatted as yyyy-MM-dd

    @Column(name = "email")
    private String email; // Client's email address

    @Column(name = "address")
    private String address; // Client's physical address

    @Column(name = "telephone")
    private String telephone; // Client's telephone number

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Timestamp when the client was created

    // Lombok will generate constructors, getters, and setters

    // Default constructor
    public Client() {

    }
}
