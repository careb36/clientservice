package com.coderhouse.clientservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for the Product entity.
 * This class is used to transfer product data between layers without exposing the entity itself.
 * It includes product information such as name, description, price, and stock.
 *
 * The @Data annotation from Lombok generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class ProductDTO {
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 55, message = "Name must be less than 55 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description must be less than 100 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be less than 0")
    private int stock;

    private boolean isActive;

    private LocalDateTime createdAt;

    // Lombok will automatically generate the getters and setters for all fields
}
