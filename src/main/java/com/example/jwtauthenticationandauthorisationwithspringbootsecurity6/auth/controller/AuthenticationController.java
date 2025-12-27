package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.controller;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.AuthenticationRequest;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.AuthenticationResponse;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.model.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AuthenticationController {

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest registerRequest);

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest authenticationRequest);
}
