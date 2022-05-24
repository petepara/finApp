package com.andersenlab.atink.service;

import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.UserProfile;

import java.util.Optional;

public interface UserProfileService {


    UserProfile saveUserProfileFromRequest(SignupRequest signUpRequest) throws ResourceNotFoundException;

    UserProfile saveUserProfile(UserProfile userProfile) throws ResourceNotFoundException;

    Optional<UserProfile> findByMobilePhone(String mobilePhone);


    Boolean existsByPassportNumber(String passportNumber);

    Optional<UserProfile> findByLogin(String login);

    void addRoleToUser(String username, String roleName) throws ResourceNotFoundException;

    UserProfile updatePushNotification(UserProfile userProfile, boolean pushNotificationStatus);

    UserProfile updateSmsNotification(UserProfile userProfile, Boolean setSmsNotificationStatus);
}



