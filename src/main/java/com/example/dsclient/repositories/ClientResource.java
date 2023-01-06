package com.example.dsclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dsclient.entities.Client;

@Repository
public interface ClientResource extends JpaRepository<Client, Long> {
    
}
