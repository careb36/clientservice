package com.coderhouse.clientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for the Client entity.
 * This class is used to transfer client data between layers without exposing the entity itself.
 * It includes basic client information such as name, email, etc.
 * <p>
 * The @Data annotation from Lombok generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class ClientDTO {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 55, message = "Name must be less than 55 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 55, message = "Last name must be less than 55 characters")
    private String lastName;

    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 55, message = "Email must be less than 55 characters")
    private String email;

    @Size(max = 55, message = "Address must be less than 55 characters")
    private String address;

    @Size(max = 20, message = "Telephone must be less than 20 characters")
    private String telephone;

    // Lombok will automatically generate the getters and setters for all fields
}

