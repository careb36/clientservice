package com.coderhouse.clientservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Data Transfer Object (DTO) for the InvoiceDetail entity.
 * This class is used to transfer invoice detail data between layers without exposing the entity itself.
 * It includes information about each item in an invoice, such as product ID, quantity, and price.
 *
 * The @Data annotation from Lombok generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */

@Data
public class InvoiceDetailsDTO {
    private int id;

    @NotNull(message = "Invoice ID is required")
    private int invoiceId;

    @NotNull(message = "Product ID is required")
    private int productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description must be less than 100 characters")
    private String description;

    private int stock;

    private LocalDateTime createdAt;

    // Lombok will automatically generate the getters and setters for all fields
}
