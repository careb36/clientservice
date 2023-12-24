package com.coderhouse.clientservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Entity class representing a detail of an invoice in the online store system.
 * This class maps to the 'invoices_details' table in the database and includes
 * fields for details of each invoiced item, such as product ID, quantity, and price.
 * <p>
 * Each InvoiceDetail is associated with an Invoice and represents an individual item or service
 * billed in that invoice.
 * <p>
 * The @Data annotation from Lombok automatically generates getters, setters,
 * and other common methods like equals, hashCode, and toString.
 * The @Setter annotation on the invoice field allows setting the associated invoice.
 * <p>
 *
 * @author: Carolina Pereira
 */
@Data
@Entity
@Table(name = "invoices_details")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "products_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoices_id") // Clave foránea manejada automáticamente
    private Invoice invoice;

    public InvoiceDetail() {
    }
}
