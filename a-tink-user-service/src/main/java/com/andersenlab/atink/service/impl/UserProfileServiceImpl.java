package com.andersenlab.atink.service.impl;

import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.Client;
import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.model.entity.Role;
import com.andersenlab.atink.model.entity.UserProfile;
import com.andersenlab.atink.repository.RoleRepository;
import com.andersenlab.atink.repository.UserProfileRepository;
import com.andersenlab.atink.service.ClientService;
import com.andersenlab.atink.service.PassportDataService;
import com.andersenlab.atink.service.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileRepository userProfileRepository;
    private ClientService clientService;
    private PassportDataService passportDataService;
    private RoleRepository roleRepository;


    @Override
    public Boolean existsByPassportNumber(String passportNumber) {
        return userProfileRepository.existsByPassportNumber(passportNumber);
    }

    @Override
    public Optional<UserProfile> findByLogin(String login) {
        if (login.startsWith("+")) {
            String mobilePhone = login.substring(1);
            return userProfileRepository.findByMobilePhone(mobilePhone);
        } else {
            return userProfileRepository.findByPassportNumber(login);
        }
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws ResourceNotFoundException {
        UserProfile userProfile = userProfileRepository.findByMobilePhone(username).orElseThrow();
        Role role = roleRepository.findByName(roleName).
                orElseThrow(() -> new ResourceNotFoundException("No such role or user"));
        userProfile.getRoles().add(role);
    }

    @Override
    @Transactional
    public UserProfile saveUserProfileFromRequest(SignupRequest signUpRequest) throws ResourceNotFoundException {
        Client client = clientService.createClientFromRequest(signUpRequest);
        UserProfile userProfile = createUserProfileFromRequest(signUpRequest);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        userProfile.setRoles(roles);
        clientService.saveClient(client);
        userProfile.setClient(client);
        return saveUserProfile(userProfile);
    }

    @Transactional
    public UserProfile createUserProfileFromRequest(SignupRequest signUpRequest) throws ResourceNotFoundException {
        PassportData passport = PassportData.builder()
                .identificationPassportNumber(signUpRequest.getIdentificationPassportNumber())
                .build();
        passportDataService.savePassport(passport);
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (userProfileRepository.existsById(id));
        return UserProfile.builder()
                .id(id)
                .mobilePhone(signUpRequest.getMobilePhone())
                .password(signUpRequest.getPassword())
                .securityQuestion(signUpRequest.getSecurityQuestion())
                .securityAnswer(signUpRequest.getSecurityAnswer())
                .email(signUpRequest.getEmail())
                .passportNumber(passport)
                .build();
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) throws ResourceNotFoundException {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userProfile.getId());
        if(userProfileOptional.isPresent()){
            throw new ResourceNotFoundException("Such user profile already exists ");
        }
        return userProfileRepository.save(userProfile);
    }
    @Override
    public UserProfile updatePushNotification(UserProfile userProfile, boolean pushNotificationStatus){
        userProfile.setPushNotification(pushNotificationStatus);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile updateSmsNotification(UserProfile userProfile, Boolean smsNotificationStatus) {
        userProfile.setSmsNotification(smsNotificationStatus);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findByMobilePhone(String mobilePhone) {
        return userProfileRepository.findByMobilePhone(mobilePhone);
    }

}
