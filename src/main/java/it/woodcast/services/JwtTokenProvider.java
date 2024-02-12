package it.woodcast.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.woodcast.resources.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${gwt.secret}")
    private String secretKey;



    // Metodo per generare un token JWT
    public String generateToken(UserResource resource) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Token scadrà tra 1 giorno

        Map<String, Object> claims = new HashMap<>();
        claims.put("name", resource.getName());
        claims.put("surname", resource.getSurname());
        claims.put("username", resource.getUsername());
        claims.put("rules", resource.getRules());



        // Aggiungi altre informazioni necessarie al token

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}