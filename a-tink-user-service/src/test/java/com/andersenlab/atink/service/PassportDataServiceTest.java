package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.repository.PassportDataRepository;
import com.andersenlab.atink.service.impl.PassportDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassportDataServiceTest {
    @Mock
    private PassportDataRepository passportDataRepository;

    @InjectMocks
    private PassportDataServiceImpl passportDataService;
    PassportData passportData;

    @BeforeEach
    public void setup() {
        passportData = PassportData.builder()
                .identificationPassportNumber("kfmfiowjfiow")
                .build();
    }

    @Test
    void returnsPassportObjectWhenItSaved() throws ResourceNotFoundException {
        doReturn(passportData).when(passportDataRepository).save(passportData);
        PassportData savedPassportData = passportDataService.savePassport(passportData);
        verify(passportDataRepository, times(1)).save(passportData);
        assertThat(savedPassportData).isNotNull();
        assertEquals(savedPassportData, passportData);
    }

    @Test
    void throwsExceptionWhenPassportDataExists() {
        doReturn(Optional.of(passportData)).when(passportDataRepository).findById(passportData.getIdentificationPassportNumber());
        assertTrue(passportDataRepository.findById(passportData.getIdentificationPassportNumber()).isPresent());
        assertThrows(ResourceNotFoundException.class, () -> {
            passportDataService.savePassport(passportData);
        });
        verify(passportDataRepository, never()).save(any(PassportData.class));
    }

}