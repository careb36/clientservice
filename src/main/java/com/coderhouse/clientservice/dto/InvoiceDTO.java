package com.coderhouse.clientservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDTO {

    private int id;
    private int clientId;
    private int quantity;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<InvoiceDetailsDTO> invoiceDetails;
}
