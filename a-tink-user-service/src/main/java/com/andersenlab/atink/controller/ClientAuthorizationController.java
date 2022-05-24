package com.andersenlab.atink.controller;

import com.andersenlab.atink.controller.dto.request.LoginRequest;
import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.controller.dto.request.TokenRefreshRequest;
import com.andersenlab.atink.controller.dto.response.JwtResponse;
import com.andersenlab.atink.controller.dto.response.TokenRefreshResponse;
import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
@AllArgsConstructor
public class ClientAuthorizationController {

    private UserProfileService userProfileService;


    @PostMapping("/register/user-profile")
    @ApiOperation(value = "Register new client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered client"),
            @ApiResponse(code = 400, message = "A client with such credentials is already exists")
    })
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws ResourceNotFoundException {
        userProfileService.saveUserProfileFromRequest(signUpRequest);
        return new ResponseEntity<>("Client registered successfully!", HttpStatus.OK);
    }

}
