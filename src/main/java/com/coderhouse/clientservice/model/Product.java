package com.coderhouse.clientservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Entity class representing a product in the online store system.
 * This class maps to the 'products' table in the database and includes
 * fields for product details such as name, price, and stock.
 * <p>
 * Products are the items available for sale in the store.
 * <p>
 * <p>
 * The @Data annotation from Lombok automatically generates getters, setters,
 * and other common methods like equals, hashCode, and toString.
 * <p>
 * @author: Carolina Pereira
 */
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "is_active")
    private boolean isActive = true;
    public Product() {
    }
}
