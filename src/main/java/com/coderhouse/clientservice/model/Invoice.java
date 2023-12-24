package com.coderhouse.clientservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing an invoice in the online store system.
 * This class maps to the 'invoices' table in the database and includes
 * fields for invoice details such as client ID, total amount, and invoice items.
 * <p>
 * It is associated with the InvoiceDetail class for item details.
 * <p>
 * <p>
 * The @Data annotation from Lombok automatically generates getters, setters,
 * and other common methods like equals, hashCode, and toString.
 * <p>
 * @author: Carolina Pereira
 */
@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clients_id")
    private int clientId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private BigDecimal total;
    public Invoice() {
    }

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> details;

    public void addDetail(InvoiceDetail detail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
        detail.setInvoice(this);
    }

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
