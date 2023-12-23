package com.coderhouse.clientservice.dto;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class InvoiceDetailsDTO {

    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private BigDecimal price;
    private String description;

}
