package com.task_tracker.Task.tracker.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.task_tracker.Task.tracker.domain.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String getToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create().withIssuer("Task Tracker")
                    .withSubject(user.getEmail())
                    .withClaim("id",user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (RuntimeException exception){
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException("Token nulo");
        }
        DecodedJWT verifier = null;

        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm).withIssuer("Task Tracker")
                    .build().verify(token);
            return verifier.getSubject();
        } catch (RuntimeException exception){
            System.out.println(exception.toString());
            throw new RuntimeException("Verifier invalido");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
    }
}
