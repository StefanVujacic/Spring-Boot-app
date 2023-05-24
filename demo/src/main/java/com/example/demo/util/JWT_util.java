package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWT_util {
    private static final SecureRandom random = new SecureRandom();
    private final int key_token = random.nextInt();

    private static final int token_validity= 3600*5;



    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }
      private <T> T getClaimFromToken(String token, Function<Claims, T> claim_resolve) {
      final Claims claims = getAllClaimsFromToken(token);
      return claim_resolve.apply(claims);
      }

      private Claims getAllClaimsFromToken (String token) {
        return Jwts.parser().setSigningKey(String.valueOf(key_token)).parseClaimsJwt(token).getBody();
      }


      public boolean validate_token (String token, UserDetails userDetails) {
          String username = getUsernameFromToken(token);
          return (username.equals(userDetails.getUsername())&& !is_token_expired(token));

      }

      private boolean is_token_expired (String token) {
      final Date expiration =get_expiration_date(token);
      return expiration.before(new Date());
    }


    private Date get_expiration_date(String token) {

        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generate_token(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ token_validity * 1000))
                .signWith(SignatureAlgorithm.HS384, String.valueOf(key_token))
                .compact();
    }

}
