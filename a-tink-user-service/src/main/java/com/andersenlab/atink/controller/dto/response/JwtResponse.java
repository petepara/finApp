package com.andersenlab.atink.controller.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ApiModel( description="Model for getting pair access token - refresh token")
public class JwtResponse {
    private String token;
    private String refreshToken;
}
