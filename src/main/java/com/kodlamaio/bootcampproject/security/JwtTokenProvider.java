package com.kodlamaio.bootcampproject.security;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${application.jwt.secretKey}")
    private String APP_SECRET;
    @Value("${application.jwt.expires.in}")
    private int EXPIRES_IN;

    public String generateJwtToken(Authentication authentication) {
        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(applicationUser.getEmail())
                .claim("authorities", applicationUser.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRES_IN))
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    String getEmailFromJwt(String token) {
        Claims claims = getJwtBody(token);
        return claims.getSubject();
    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    boolean isTokenExpired(String token) {
        Date expiration = getJwtBody(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getJwtBody(String token) {

        return Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }


}
