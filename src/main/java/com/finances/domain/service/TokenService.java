package com.finances.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.finances.domain.dto.token.TokenDto;
import com.finances.domain.model.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class TokenService {

//    TODO
//    should configure rsa
    public TokenDto tokenGenerator(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            String jwt = JWT.create()
                    .withSubject(user.getEmail())
                    .withClaim("chave test", "valor teste")
                    .withIssuer("quem é o auth server")
                    .withExpiresAt(new Date().toInstant().plus(Duration.ofMinutes(30)))
                    .sign(algorithm);

            return new TokenDto(jwt, "Bearer", new Date().toInstant().plus(Duration.ofMinutes(30)));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("teste");
        }


    }
}
