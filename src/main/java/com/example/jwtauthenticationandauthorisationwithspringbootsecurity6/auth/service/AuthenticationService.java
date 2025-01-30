package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.service;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.AuthenticationRequest;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.AuthenticationResponse;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.auth.RegisterRequest;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.configuration.JwtService;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.enums.Role;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.model.User;
import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstname(registerRequest.getFirstName())
                .lastname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
