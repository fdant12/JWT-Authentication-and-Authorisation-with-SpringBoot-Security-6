package com.example.jwtauthenticationandauthorisationwithspringbootsecurity6.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "5c687046695f6b417568217e48754e496f5f4a377d20683f4950636a3864672d";

    public String extractUsername(String token) {
        return null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Get all core infos that JWT transmits (i.e., user identity, permissions, expiration of JWT, to name a few).
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /* *
     * secret use to digitally sign the jwt to ensure
     * the message wasn't changed and was sent by who is claim
     * to be (verifying the sender)
     * */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}