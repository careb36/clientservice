package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Métodos personalizados, por ejemplo, buscar por nombre
    // List<Product> findByNameContaining(String name);
}
