package com.murali.studentmanagement.Jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
public static String Secret="mysecretkeymysecretkeymysecretkey123456";
public final Key key=Keys.hmacShaKeyFor(Secret.getBytes());
public String generateToken(String username)
{
    return Jwts.builder()
           .setSubject(username)
           .setIssuedAt(new Date())
           .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
           .signWith(key,SignatureAlgorithm.HS256)
           .compact();
}
public String extractUsername(String token)
{ 
     return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token)
    {
        try{
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

            return true;    
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
