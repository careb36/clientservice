package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Product entity.
 * This interface allows for managing Product entities via Spring Data JPA.
 * It extends JpaRepository, providing essential CRUD operations for the Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
// Basic CRUD operations for Product entity are automatically provided by JpaRepository.
}
