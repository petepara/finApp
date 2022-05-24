package com.andersenlab.atink.service;

import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client saveClient(Client client) throws ResourceNotFoundException;

    Optional<Client> findById(UUID id);

    Client createClientFromRequest(SignupRequest signupRequest);

    void deleteClient(UUID id);

    Boolean existsById(UUID id);

    Client findByFirstName(String name);
}
