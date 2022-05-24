package com.andersenlab.atink.controller;

import com.andersenlab.atink.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
@AllArgsConstructor
public class PasswordController {
    private final UserProfileService userProfileService;

}
