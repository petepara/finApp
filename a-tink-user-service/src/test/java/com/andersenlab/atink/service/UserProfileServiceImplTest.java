package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.model.entity.UserProfile;
import com.andersenlab.atink.repository.UserProfileRepository;
import com.andersenlab.atink.service.impl.UserProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {
    @Mock
    private UserProfileRepository userProfileRepository;
    @Mock
    private PasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserProfileServiceImpl userProfileService;
    UserProfile userProfile;

    @BeforeEach
    public void setup() {
        userProfile = UserProfile.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"))
                .passportNumber(PassportData.builder()
                        .identificationPassportNumber("vfreberbe").build())
                .client(null)
                .securityAnswer("da?")
                .securityAnswer("da")
                .mobilePhone("111111111111")
                .password("vfrevger")
                .build();
    }

    @Test
    void returnUserProfileWhenItSaved() throws ResourceNotFoundException {
        doReturn(userProfile).when(userProfileRepository).save(userProfile);
        UserProfile savedUserProfile = userProfileService.saveUserProfile(userProfile);
        verify(userProfileRepository, times(1)).save(userProfile);
        assertThat(savedUserProfile).isNotNull();
        assertEquals(savedUserProfile, userProfile);
    }

    @Test
    void throwsExceptionWhenClientExists() {
        doReturn(Optional.of(userProfile)).when(userProfileRepository).findById(userProfile.getId());
        assertTrue(userProfileRepository.findById(userProfile.getId()).isPresent());
        assertThrows(ResourceNotFoundException.class, () -> {
            userProfileService.saveUserProfile(userProfile);
        });
        verify(userProfileRepository, never()).save(any(UserProfile.class));
    }

    @Test
    void shouldReturnUserProfileWithNewPassword() {
        doReturn(userProfile).when(userProfileRepository).save(userProfile);
        doReturn(Optional.of(userProfile)).when(userProfileRepository).findByMobilePhone(userProfile.getMobilePhone());
        doReturn("$2a$12$iyPOyZws8wK7GMcBdHTbo./YbW52oqa5B9FzDMgmKS.M3XlCpjTIG").when(bCryptPasswordEncoder).encode("newPassword");
        UserProfile updatedUserProfile = userProfileService.updatePassword("newPassword", "111111111111");

        assertThat(updatedUserProfile.getPassword()).isEqualTo("$2a$12$iyPOyZws8wK7GMcBdHTbo./YbW52oqa5B9FzDMgmKS.M3XlCpjTIG");

    }

    @Test
    void returnsTrueIfUserProfileExistsWithSuchPassportNUmber() {
        doReturn(Boolean.TRUE).when(userProfileRepository).existsByPassportNumber(userProfile.getPassportNumber().getIdentificationPassportNumber());
        assertTrue(userProfileService.existsByPassportNumber(userProfile.getPassportNumber().getIdentificationPassportNumber()));
        verify(userProfileRepository, times(1)).existsByPassportNumber(userProfile.getPassportNumber().getIdentificationPassportNumber());
    }

    @Test
    void returnsUserProfileIfUserProfileExistsWithPassportOrPhone() {
        doReturn(Optional.of(userProfile)).when(userProfileRepository).findByMobilePhone(userProfile.getMobilePhone());
        doReturn(Optional.of(userProfile)).when(userProfileRepository).findByPassportNumber(userProfile.getPassportNumber().getIdentificationPassportNumber());
        assertTrue(userProfileService.findByLogin("+" + userProfile.getMobilePhone()).isPresent());
        assertTrue(userProfileService.findByLogin(userProfile.getPassportNumber().getIdentificationPassportNumber()).isPresent());
        UserProfile saveUserProfileMobilePhone = userProfileService.findByLogin("+" + userProfile.getMobilePhone()).get();
        UserProfile saveUserProfilePassportNumber = userProfileService.findByLogin(userProfile.getPassportNumber().getIdentificationPassportNumber()).get();
        assertEquals(saveUserProfileMobilePhone, userProfile);
        assertEquals(saveUserProfilePassportNumber, userProfile);
    }

}