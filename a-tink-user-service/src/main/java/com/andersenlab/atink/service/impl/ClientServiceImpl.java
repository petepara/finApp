package com.andersenlab.atink.service.impl;

import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.constant.ClientStatus;
import com.andersenlab.atink.model.entity.Client;
import com.andersenlab.atink.repository.ClientRepository;
import com.andersenlab.atink.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) throws ResourceNotFoundException {
        Optional<Client> savedClient = clientRepository.findById(client.getId());
        if(savedClient.isPresent()){
            throw new ResourceNotFoundException("Client already exist with given ID:" + client.getId());
        }
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client createClientFromRequest(SignupRequest signupRequest) {
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (clientRepository.existsById(id));
        return Client.builder()
                .id(id)
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .accessionDate(LocalDate.now())
                .clientStatus(ClientStatus.NOT_ACTIVE)
                .mobilePhone(signupRequest.getMobilePhone().substring(1))
                .countryOfResidence(signupRequest.getCountryOfResidence())
                .build();
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(UUID id) {
        return clientRepository.existsById(id);
    }

    @Override
    public Client findByFirstName(String name) {
        return clientRepository.findByFirstName(name);
    }

}
