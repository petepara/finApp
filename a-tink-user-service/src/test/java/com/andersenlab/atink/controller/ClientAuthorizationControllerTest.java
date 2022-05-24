package com.andersenlab.atink.controller;

import com.andersenlab.atink.controller.dto.request.SignupRequest;
import com.andersenlab.atink.model.entity.PassportData;
import com.andersenlab.atink.model.entity.UserProfile;
import com.andersenlab.atink.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ClientAuthorizationControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private ClientAuthorizationController clientController;

    private UserProfile userProfile;
    private SignupRequest signupRequest;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();

        userProfile = UserProfile.builder()
                .id(UUID.fromString("22d29f2d-4a8a-40fc-a8fb-e15bb4b9cb2c"))
                .passportNumber(PassportData.builder()
                        .identificationPassportNumber("1fregf19g1reg").build())
                .client(null)
                .securityAnswer("da?")
                .securityAnswer("da")
                .mobilePhone("375445682751")
                .password("123")
                .build();
        signupRequest = SignupRequest.builder()
                .firstName("Petro")
                .lastName("Paramonidze")
                .countryOfResidence("BLR")
                .mobilePhone("375445682751")
                .password("123")
                .identificationPassportNumber("1fregf19g1reg")
                .securityQuestion("da?")
                .securityAnswer("da")
                .build();
    }

    @Test
    void registerUser() throws Exception {
        ResultActions response = mockMvc.perform(
                post("/user-service/register/user-profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}