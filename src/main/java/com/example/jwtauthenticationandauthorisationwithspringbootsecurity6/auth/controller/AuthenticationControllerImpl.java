package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.controller;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.AuthenticationRequest;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.AuthenticationResponse;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.RegisterRequest;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequest));
    }
}
