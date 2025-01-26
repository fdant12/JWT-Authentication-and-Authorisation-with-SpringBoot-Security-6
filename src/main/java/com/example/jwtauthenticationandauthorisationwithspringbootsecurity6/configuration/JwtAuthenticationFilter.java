package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.configuration;

import com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Create a constructor with any final field that we declare
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain // contains the list of filter that we need
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // To pass jwt authentication Token within the header when we make a call
        final String jwt;
        final String username;

        // Should always start with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        // If the user is not authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get user's infos from the repository
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // check if the user is valid
            if (jwtService.isValidToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // update the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            // pass the hand to the next filter to be executed
            filterChain.doFilter(request, response);

        }
    }
}
