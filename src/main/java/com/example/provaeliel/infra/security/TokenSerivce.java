package com.example.provaeliel.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.provaeliel.models.UserModel;


@Service
public class TokenSerivce {
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(UserModel user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
            .withIssuer("auth-api")
            .withSubject(user.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("error while generating token", e);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("auth-api")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error while validating token", e);

        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
