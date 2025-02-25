package com.beyond.university.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class jwtTokenProvider {
    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;
    private static final long ACCESS_TOKEN_EXP = 1000L * 60L * 15L; // 15분

    public jwtTokenProvider(@Value("${springboot.jwt.secret}")String secret,
                            UserDetailsService userDetailsService) {
        log.debug("Secret : {}", secret);
        // 바이트 배열로 가지고 와서 사용
        this.secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
        this.userDetailsService = userDetailsService;
    }

    // AccessToken을 생성하는 메소드
    public String createAccessToken(String username, String role) {
        Map<String, String> claims = new HashMap<>();

        claims.put("username", username);
        claims.put("role", role);

        return createToken(claims,ACCESS_TOKEN_EXP);
    }

    private String createToken(Map<String, String> claims, long tokenExp) {
        return Jwts.builder()
                .claims(claims)                                             // 공개 클래임
                .id(Long.toHexString(System.nanoTime()))                    // jit(JWT ID) 클래임
                .issuedAt(new Date())                                       // 발급 시간 설정
                .expiration(new Date(System.currentTimeMillis() + tokenExp))// 만료 시간 설정
                .signWith(secretKey)                                        // 서명 생성
                .compact();                                                 // JWT 토큰 생성
    }

    // 토큰이 유효한지 체크하는 메소드
    public boolean validateToken(String token) {
        // 클레임 추출
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        
        // 클레임 만료 기간 반환하여 현재 날짜 이전인지 비교 (FALSE TRUE)
        return !claims.getPayload().getExpiration().before(new Date());
    }
    
    // 클라이언트가 헤더를 통해 서버로 전달한 토큰을 추출하는 메소드
    public String resolveToken(String bearerToken) {
        // 토큰이 존재한다면 Bearer 부분을 제외하고 반환
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    // SecurityContextHolder에 저장할 Authentication 객체를 생성하는 메소드
    public Authentication getAuthentication(String token) {
        String username = getUserName(token);
        
        // 유저이름으로 유저 정보를 가져와서
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        // 상세 토큰 생성
        return new UsernamePasswordAuthenticationToken(
                userDetails, 
                null, 
                userDetails.getAuthorities()
        );
    }

    private String getUserName(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username")
                .toString();
    }
}
