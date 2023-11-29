package com.coderhouse.clientservice.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for the Client entity.
 * This class is used to transfer client data between layers without exposing the entity itself.
 * It includes basic client information such as name, email, etc.
 *
 * The @Data annotation from Lombok generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class ClientDTO {

    /**
     * The unique identifier of the client.
     */
    private Long id;

    /**
     * The first name of the client.
     */
    private String name;

    /**
     * The last name of the client.
     */
    private String lastName;

    /**
     * The birth date of the client.
     */
    private LocalDate birthDate;

    /**
     * The email address of the client.
     */
    private String email;

    /**
     * The physical address of the client.
     */
    private String address;

    /**
     * The telephone number of the client.
     */
    private String telephone;

    // Lombok will automatically generate the getters and setters for all fields
}

