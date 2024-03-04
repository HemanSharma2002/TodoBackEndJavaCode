package com.spring.todoapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class JwtAuthenticationResource {
    @Autowired
    private JwtEncoder jwtEncoder;
    @PostMapping("/authentication")
    public JwtResponse authenticate(Authentication authentication){
//        return authentication;
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        var claims=JwtClaimsSet.builder().issuer("self").issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(24*60*60))
                .subject(authentication.getName())
                .claim("scope",createScope(authentication))
                .build();
        var parameters= JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }
    public String createScope(Authentication authentication){
        return authentication.getAuthorities().stream()
                .map(a->a.getAuthority())
                .collect(Collectors.joining(" "));
    }

    record JwtResponse(String token){}
}

