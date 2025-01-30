package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
}
