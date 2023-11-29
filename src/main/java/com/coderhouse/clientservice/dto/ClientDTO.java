package com.coderhouse.clientservice.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClientDTO {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String address;
    private String telephone;

    // Lombok generará automáticamente los getters y setters para todos los campos
}
