//package com.travel.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//public class JwtUtil {
//
//    // Strong Secret Key 
//    private static final SecretKey SECRET_KEY = 
//            Keys.hmacShaKeyFor("mySuperSecretKeyForTravelManagementSystem1234567890".getBytes());
//
//    // Generate Token
//    public static String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(SECRET_KEY)
//                .compact();
//    }
//
//    // Extract Email from Token
//    public static String extractEmail(String token) {
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            return claims.getSubject();
//        } catch (Exception e) {
//            System.out.println("JWT Parse Error: " + e.getMessage());
//            return null;
//        }
//    }
//}



package com.travel.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET =
            "mySuperSecretKeyForTravelManagementSystem1234567890";

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION = 1000 * 60 * 60 * 10;

    // ================= GENERATE TOKEN =================
    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    // ================= EXTRACT EMAIL =================
    public static String extractEmail(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (Exception e) {
            System.err.println("Error extracting email: " + e.getMessage());
            return null;
        }
    }

    // ================= EXTRACT ROLE =================
    public static String extractRole(String token) {
        try {
            String role = getClaims(token).get("role", String.class);
            return role != null ? role : "EMPLOYEE"; // DEFAULT ROLE
        } catch (Exception e) {
            System.err.println("Error extracting role: " + e.getMessage());
            return null;
        }
    }

    // ================= VALIDATE =================
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}