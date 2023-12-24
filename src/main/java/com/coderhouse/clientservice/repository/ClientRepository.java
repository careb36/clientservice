package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Client entity.
 * This interface is a part of the Spring Data JPA infrastructure for data access.
 * It extends JpaRepository, providing CRUD operations and additional query methods for Client objects.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Basic CRUD operations for Product entity are automatically provided by JpaRepository.
}
