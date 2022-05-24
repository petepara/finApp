package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.model.entity.UserProfile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private PassportDataRepository passportDataRepository;

    @BeforeEach
    public void setup() {
        passportDataRepository.save(PassportData.builder()
                .identificationPassportNumber("vfreberbe")
                .build());
        userProfileRepository.save(UserProfile.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"))
                .passportNumber(PassportData.builder()
                        .identificationPassportNumber("vfreberbe").build())
                .client(null)
                .securityAnswer("da?")
                .securityAnswer("da")
                .mobilePhone("111111111111")
                .password("vfrevger")
                .build());

    }
    @AfterEach
    public void destroyAll(){
        userProfileRepository.deleteAll();
        passportDataRepository.deleteAll();
    }

    @Test
    void findByMobilePhone() {
        UserProfile userProfile = userProfileRepository.findByPassportNumber("vfreberbe").get();

        Assertions.assertThat(userProfile.getMobilePhone()).isEqualTo("111111111111");
    }

    @Test
    void isSmsNotificationEnable() {
        UserProfile userProfile = userProfileRepository.findByPassportNumber("vfreberbe").get();
        userProfile.setSmsNotification(true);
        assertTrue(userProfile.isSmsNotification());
    }

    @Test
    void isPushNotificationEnable() {
        UserProfile userProfile = userProfileRepository.findByPassportNumber("vfreberbe").get();
        userProfile.setPushNotification(true);
        assertTrue(userProfile.isPushNotification());
    }

    @Test
    void existsByPassportNumber() {
        assertTrue(userProfileRepository.findByPassportNumber("vfreberbe").isPresent());
    }

    @Test
    void findByPassportNumber() {
        UserProfile userProfile = userProfileRepository.findByPassportNumber("vfreberbe").get();

        Assertions.assertThat(userProfile.getMobilePhone()).isEqualTo("111111111111");

    }

    @Test
    void existsByMobilePhoneAndPassword() {
        assertTrue(userProfileRepository.existsByMobilePhoneAndPassword("111111111111", "vfrevger" ));
    }

    @Test
    void existsByPassportNumberAndPassword() {
        assertTrue(userProfileRepository.existsByPassportNumberAndPassword("vfreberbe", "vfrevger" ));
    }

    @Test
    void updatePassword() {
        UserProfile userProfile = userProfileRepository.findByPassportNumber("vfreberbe").get();
        userProfileRepository.updatePassword("1234", "111111111111");
        assertTrue(userProfileRepository.existsByMobilePhoneAndPassword("111111111111","1234"));
    }
    @Test
    void saveUserProfile(){
        passportDataRepository.save(PassportData.builder()
                .identificationPassportNumber("12345678")
                .build());
        userProfileRepository.save(UserProfile.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2b"))
                .passportNumber(PassportData.builder()
                        .identificationPassportNumber("12345678").build())
                .client(null)
                .securityAnswer("da?")
                .securityAnswer("da")
                .mobilePhone("222222222222")
                .password("1379")
                .build());
        assertTrue(userProfileRepository.existsByPassportNumber("12345678"));
    }

    @Test
    void returnNullWhenDeleteUserProfile(){
        userProfileRepository.delete(userProfileRepository.findByPassportNumber("vfreberbe").get());
        assertTrue(userProfileRepository.findByPassportNumber("vfreberbe").isEmpty());
    }

}