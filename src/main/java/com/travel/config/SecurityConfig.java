package com.travel.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // 🚨 DISABLE CSRF
            .csrf(AbstractHttpConfigurer::disable)

            // 🚨 ENABLE CORS
            .cors(cors -> cors.configurationSource(request -> {

                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedOrigins(
                        List.of("http://localhost:5173")
                );

                config.setAllowedMethods(
                        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
                );

                config.setAllowedHeaders(List.of("*"));

                config.setAllowCredentials(true);

                return config;
            }))

            // 🚨 VERY IMPORTANT
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🚨 DISABLE DEFAULT LOGIN FORM
            .formLogin(AbstractHttpConfigurer::disable)

            // 🚨 DISABLE HTTP BASIC
            .httpBasic(AbstractHttpConfigurer::disable)

            // 🚨 AUTHORIZATION
            .authorizeHttpRequests(auth -> auth

                    // 🔥 ALLOW LOGIN
                    .requestMatchers("/auth/**").permitAll()

                    // everything else open temporarily
                    .anyRequest().permitAll()
            );

        return http.build();
    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


