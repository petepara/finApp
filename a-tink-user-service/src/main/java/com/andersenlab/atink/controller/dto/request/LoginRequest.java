package com.andersenlab.atink.controller.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel( description="Model for login")
public class LoginRequest {
    private String username;
    private String password;
}
