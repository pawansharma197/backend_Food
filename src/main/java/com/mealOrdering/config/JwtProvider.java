package com.mealOrdering.config;

import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collection;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Set;
import java.util.HashSet;


@Service
public class JwtProvider {
	
	// generate token
	
	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	private final long jwtExpirationInMillis = 86400000;

    public  String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        // Corrected the syntax
        String jwt = Jwts.builder()
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMillis)) // Set expiration date
                .claim("email", auth.getName()) // Add email claim
                .claim("authorities", roles) // Add roles claim
                .signWith(key) // Sign the JWT with the secret key
                .compact(); // Create the token as a compact String

        return jwt;
    }

    // Get email from JWT Token
    
    public String getEmailFromJwtToken(String jwt)
    {
    	jwt = jwt.substring(7);
    	Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();	
		String email = String.valueOf(claims.get("email"));
		
		return email;   	
    	
    }
    
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }

        return String.join(",", auths); // Join roles into a single string
    }
	   

}
