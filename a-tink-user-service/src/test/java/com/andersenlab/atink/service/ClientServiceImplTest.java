package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.constant.ClientStatus;
import com.andersenlab.atink.model.entity.Client;
import com.andersenlab.atink.repository.ClientRepository;
import com.andersenlab.atink.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;
    Client client;

    @BeforeEach
    public void setup() {
        client = Client.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"))
                .firstName("Eduard")
                .lastName("Petrenko")
                .accessionDate(LocalDate.now())
                .clientStatus(ClientStatus.NOT_ACTIVE)
                .mobilePhone("3752950514734")
                .countryOfResidence("BLR")
                .build();
    }

    @Test
    void returnsClientWhenItSaved() throws ResourceNotFoundException {
        doReturn(client).when(clientRepository).save(client);
        Client savedClient = clientService.saveClient(client);
        verify(clientRepository, times(1)).save(client);
        assertThat(savedClient).isNotNull();
        assertEquals(savedClient, client);
    }

    @Test
    void throwsExceptionWhenClientExists() {
        doReturn(Optional.of(client)).when(clientRepository).findById(client.getId());
        assertTrue(clientRepository.findById(client.getId()).isPresent());
        assertThrows(ResourceNotFoundException.class, () -> {
            clientService.saveClient(client);
        });
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    void returnClientWhenFindByIdExists() {
        doReturn(Optional.of(client)).when(clientRepository).findById(client.getId());
        assertTrue(clientService.findById(client.getId()).isPresent());
        Client savedClient = clientService.findById(client.getId()).get();
        assertEquals(savedClient, client);
    }

    @Test
    void deleteById() {
        UUID clientId = UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb24");
        doNothing().when(clientRepository).deleteById(clientId);
        clientService.deleteClient(clientId);
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void shouldReturnTrueWhenExists() {
        doReturn(Boolean.TRUE).when(clientRepository).existsById(client.getId());
        assertTrue(clientService.existsById(client.getId()));
        verify(clientRepository, times(1)).existsById(client.getId());
    }
}