package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Client entity.
 * This interface provides CRUD operations and query methods for Client objects.
 * It extends JpaRepository, leveraging Spring Data's repository abstraction.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Standard CRUD operations and any custom queries can be defined here.
    // Since it extends JpaRepository, basic CRUD methods are already provided.
}
