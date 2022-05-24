package com.andersenlab.atink.service.impl;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.repository.PassportDataRepository;
import com.andersenlab.atink.service.PassportDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PassportDataServiceImpl implements PassportDataService {
    private final PassportDataRepository passportDataRepository;

    @Override
    public PassportData savePassport(PassportData passportData) throws ResourceNotFoundException {
        Optional<PassportData> savedPassport = passportDataRepository.findById(passportData.getIdentificationPassportNumber());
        if(savedPassport.isPresent()){
            throw new ResourceNotFoundException("Passport already exist with given ID:" + passportData.getIdentificationPassportNumber());
        }
        return passportDataRepository.save(passportData);
    }
}
