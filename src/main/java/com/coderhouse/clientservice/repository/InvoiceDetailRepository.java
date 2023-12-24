package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for InvoiceDetail entity.
 * This interface extends JpaRepository, allowing CRUD operations for InvoiceDetail entities.
 * It provides an abstraction layer for data access in the application.
 */
@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    // Basic CRUD operations for Product entity are automatically provided by JpaRepository.

}
