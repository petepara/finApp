package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.entity.PassportData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDataRepository extends JpaRepository<PassportData, String> {

}
