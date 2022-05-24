package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.constant.ClientStatus;
import com.andersenlab.atink.model.entity.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;
    @BeforeEach
    void setup(){
        clientRepository.save(Client.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"))
                .firstName("Kolya")
                .lastName("Nikolaev")
                .accessionDate(LocalDate.now())
                .clientStatus(ClientStatus.NOT_ACTIVE)
                .mobilePhone("111111111111")
                .countryOfResidence("RUS")
                .build());
    }
    @AfterEach
    public void destroyAll(){
        clientRepository.deleteAll();
    }
    @Test
    void returnTrueWhenSaveClient(){
        clientRepository.save(Client.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2b"))
                .firstName("Kolya")
                .lastName("Nikolaev")
                .accessionDate(LocalDate.now())
                .clientStatus(ClientStatus.NOT_ACTIVE)
                .mobilePhone("111111111112")
                .countryOfResidence("RUS")
                .build());
        assertTrue(clientRepository.findById(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2b")).isPresent());
    }
    @Test
    void returnNullWhenDeleteClient(){
        clientRepository.deleteById(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"));
        assertTrue(clientRepository.findById(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c")).isEmpty());
    }
    @Test
    void returnTrueIfClientExists(){
        assertTrue(clientRepository.findById(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c")).isPresent());
    }
}