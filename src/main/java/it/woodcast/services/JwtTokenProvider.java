package it.woodcast.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.woodcast.enumeration.RulesEnum;
import it.woodcast.resources.JwtUser;
import it.woodcast.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

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
        claims.put("userID", resource.getId());


        // Aggiungi altre informazioni necessarie al token

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public  JwtUser parseToken(String token) {
         JwtUser jwtUser = new JwtUser();

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseClaimsJws(token);
        Map<String, Object> claimMap = claimsJws.getPayload();
        jwtUser.setUsername((String) claimMap.get("username"));
        jwtUser.setUserId(Integer.toString((Integer) claimMap.get("userID")));
        List<RulesEnum> rules = new ArrayList<>();
        for (String rule : ((List<String>) claimMap.get("rules"))) {
            rules.add(RulesEnum.valueOf(rule));
        }

        jwtUser.setRules(rules);
        return jwtUser;
    }
}