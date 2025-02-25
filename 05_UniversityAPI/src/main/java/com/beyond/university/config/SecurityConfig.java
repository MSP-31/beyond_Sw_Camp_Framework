package com.beyond.university.config;

import com.beyond.university.auth.jwt.JwtAuthenticationFilter;
import com.beyond.university.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)      //비활성화
                .cors(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)// 비활성화
                .formLogin(AbstractHttpConfigurer::disable)// 비활성화
                // JWT 토큰으로 인증을 처리하기 때문에 세션 사용하지 않는다.
                .sessionManagement(ssesion ->
                        ssesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하는 설정
                // JwtAuthenticationFilter에서 인증이 정상적으로 처리되면 UsernamePasswordAuthenticationFilter는 자동으로 통과한다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(requests ->{
                        // Swagger
                        requests.requestMatchers("/api-docs/", "/swagger-ui/", "/actuator/health").permitAll();
                        // 모든 GET 요청의 경우 허용
                        requests.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();
                        // 이 외의 모든 요청은 인증이 필요
                        requests.anyRequest().authenticated();
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
