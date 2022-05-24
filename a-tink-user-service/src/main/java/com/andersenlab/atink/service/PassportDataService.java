package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.PassportData;

public interface PassportDataService {
     PassportData savePassport(PassportData passportData) throws ResourceNotFoundException;
}
