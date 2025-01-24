package com.backend.offer.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    String secretKey;
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (ExpiredJwtException ex) {
            System.out.println("Token scaduto: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Formato del token non supportato: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("Token malformato: " + ex.getMessage());
        } catch (SignatureException ex) {
            System.out.println("Firma non valida: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Token nullo o vuoto: " + ex.getMessage());
        } catch (WeakKeyException ex) {
            System.out.println("Chiave debole: " + ex.getMessage());
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
