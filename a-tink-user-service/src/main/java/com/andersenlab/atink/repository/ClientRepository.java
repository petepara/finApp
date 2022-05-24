package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByFirstName(String name);

}
