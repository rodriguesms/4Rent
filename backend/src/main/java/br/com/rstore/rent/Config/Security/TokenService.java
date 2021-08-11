package br.com.rstore.rent.Config.Security;

import br.com.rstore.rent.Models.Owner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${4rent.jwt.expiration}")
    private String expirationTime;

    @Value("${4rent.jwt.secret}")
    private String secret;

    public String tokenGenerate(Authentication authentication) {
        Owner loggedOwner = (Owner) authentication.getPrincipal();

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expirationTime));

        return Jwts.builder()
                .setIssuer("4Rent API")
                .setSubject(loggedOwner.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getOwnerId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
