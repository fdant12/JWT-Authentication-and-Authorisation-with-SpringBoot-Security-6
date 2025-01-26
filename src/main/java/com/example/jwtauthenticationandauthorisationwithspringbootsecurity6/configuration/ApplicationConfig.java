package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.configuration;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration
public class ApplicationConfig {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService () {
      return username -> repository.findByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    };
}
