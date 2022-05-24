package com.andersenlab.atink.controller.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( description="Model for registration")
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private String mobilePhone;
    private String password;
    private String identificationPassportNumber;
    private String email;
    private String securityQuestion;
    private String securityAnswer;
}
