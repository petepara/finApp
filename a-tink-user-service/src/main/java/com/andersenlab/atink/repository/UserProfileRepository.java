package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByMobilePhone(String mobilePhone);

    @Query("SELECT up.smsNotification FROM UserProfile up where up.mobilePhone=:phone")
    Boolean isSmsNotificationEnable(@Param("phone") String mobilePhone);

    @Query("SELECT up.pushNotification FROM UserProfile up where up.mobilePhone=:phone")
    Boolean isPushNotificationEnable(@Param("phone") String mobilePhone);

    @Query("SELECT COUNT(up.id)=1 FROM UserProfile up WHERE up.passportNumber.identificationPassportNumber=:passportNumber")
    Boolean existsByPassportNumber(String passportNumber);
    @Query("SELECT up FROM UserProfile up WHERE up.passportNumber.identificationPassportNumber=:passportNumber")
    Optional<UserProfile> findByPassportNumber(String passportNumber);

    boolean existsByMobilePhoneAndPassword(String mobilePhone, String password);

    @Query("SELECT COUNT(up.id)=1 FROM UserProfile up WHERE up.passportNumber.identificationPassportNumber=:passportNumber and up.password=:password")
    Boolean existsByPassportNumberAndPassword(String passportNumber, String password);

    @Modifying
    @Query("update UserProfile up set up.password = :password where up.mobilePhone = :mobilePhone")
    void updatePassword(@Param(value = "password") String newPassword, @Param(value = "mobilePhone") String mobilePhone);


}
