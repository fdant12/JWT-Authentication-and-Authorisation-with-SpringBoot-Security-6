package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.repositories;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.model.User;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
