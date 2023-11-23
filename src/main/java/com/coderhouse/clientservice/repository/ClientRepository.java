package com.coderhouse.clientservice.repository;

import com.coderhouse.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{

}