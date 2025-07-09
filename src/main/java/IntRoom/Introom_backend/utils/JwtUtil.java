////package IntRoom.Introom_backend.utils;
////
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import lombok.NonNull;
////import org.springframework.stereotype.Component;
////
////import java.util.Date;
////
////@Component
////public class JwtUtil {
////    private final String secret = "introoms_secret_key";
////    private final long expirationMs =  8640000;
////
////    public String generateToken(String email) {
////        return Jwts.builder()
////                .setSubject(email)
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
////                .signWith(SignatureAlgorithm.HS512, secret)
////                .compact();
////    }
////    public String extractEmail(String token) {
////        return Jwts.parser()
////                .setSigningKey(secret)
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
////    public boolean validateToken(String token) {
////        try {
////            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
////            return true;
////        } catch (Exception e) {
////            return false;
////        }
////    }
////
////
////
////}
//
//package IntRoom.Introom_backend.utils;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    // Key must be at least 256 bits for HS512 (32 characters)
//    private final String secret = "introoms_secret_key_introoms_secret_key"; // 32+ characters
//    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
//    private final long expirationMs = 86400000L; // 1 day in milliseconds
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public String extractEmail(String token) {
//        Claims claims = Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts
//                    .parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (JwtException e) {
//            System.out.println("JWT error: " + e.getMessage());
//            return false;
//        }
//    }
//}
//


//package IntRoom.Introom_backend.utils;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET = "introoms_secret_key_123456789012345678901234"; // minimum 32 characters
//    private final long EXPIRATION = 86400000; // 1 day
//    private Key key;
//
//    @PostConstruct
//    public void init() {
//        key = Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractEmail(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//}
package IntRoom.Introom_backend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "introoms_secret_key_which_should_be_long_enough_for_hmac";
    private final long expirationMs = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
