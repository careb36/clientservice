package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Invoice entity.
 * Facilitates data access operations for the Invoice entity, extending JpaRepository.
 * This interface provides standardized CRUD operations for Invoice entities.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    // Basic CRUD operations for Product entity are automatically provided by JpaRepository.
}
