package com.andersenlab.atink.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "passport_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassportData {

    @Id
    @Column( name ="identification_passport_number")
    private String identificationPassportNumber;

    @Column(name ="issuance_date")
    private LocalDate issuanceDate;

    @Column(name ="expiry_date")
    private LocalDate expiryDate;

    @Column( name ="nationality")
    private String nationality;

    @Column(name ="birth_date")
    private LocalDate birthDate;
}