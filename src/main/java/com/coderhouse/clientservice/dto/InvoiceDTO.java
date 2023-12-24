package com.coderhouse.clientservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
/**
 * Data Transfer Object (DTO) for the Invoice entity.
 * This class is used to transfer invoice data between layers without exposing the entity itself.
 * It includes information such as client ID, total amount, and details of each item in the invoice.
 *
 * The @Data annotation from Lombok generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 */

@Data
public class InvoiceDTO {
    private int id;

    @NotNull(message = "Client ID is required")
    private int clientId;

    @NotNull(message = "Total is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total must be greater than 0")
    private BigDecimal total;

    private OffsetDateTime createdAt;

    @NotEmpty(message = "Invoice details cannot be empty")
    private List<InvoiceDetailsDTO> invoiceDetails;

    // Lombok will automatically generate the getters and setters for all fields
}
