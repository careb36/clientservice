package com.coderhouse.clientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 55, message = "Name must be less than 55 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 55, message = "Last name must be less than 55 characters")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 55, message = "Address must be less than 55 characters")
    @Column(name = "address")
    private String address;

    @Size(max = 20, message = "Telephone must be less than 20 characters")
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Lombok will generate constructors, getters, and setters

    // Default constructor
    public Client() {

    }
}
