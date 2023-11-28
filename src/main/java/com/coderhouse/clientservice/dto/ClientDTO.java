package com.coderhouse.clientservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientDTO {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String address;
    private String telephone;

    public void setId(int id) {
    }

    // Lombok generará automáticamente los getters y setters para todos los campos
}
