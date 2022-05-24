package com.andersenlab.atink.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserProfile {
    @Id
    private UUID id;

    @Column(name ="sms_notification")
    @Builder.Default
    private boolean smsNotification = true;

    @Column(name ="push_notification")
    @Builder.Default
    private boolean pushNotification = true;

    @Column( name ="password")
    private String password;

    @Column( name ="email")
    private String email;

    @Column( name ="security_question")
    private String securityQuestion;

    @Column( name ="security_answer")
    private String securityAnswer;

    @Column(name ="app_registration_date")
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appRegistrationDate;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "passport_number",
            referencedColumnName = "identification_passport_number"

    )
    private PassportData passportNumber;
    @Column(name ="email_subscription")
    private boolean emailSubscription;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_profile_roles", joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Client client;
}
