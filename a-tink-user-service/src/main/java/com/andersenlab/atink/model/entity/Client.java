package com.andersenlab.atink.model.entity;

import com.andersenlab.atink.model.constant.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    private UUID id;

    @Column( name ="first_name")
    private String firstName;

    @Column( name ="last_name")
    private String lastName;

    @Column( name ="middle_name")
    private String middleName;

    @Column( name ="country_of_residence")
    private String countryOfResidence;

    @Column(name ="accession_date")
    private LocalDate accessionDate;

    @Column( name ="client_status")
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    @Column( name ="mobile_phone")
    private String mobilePhone;

}
