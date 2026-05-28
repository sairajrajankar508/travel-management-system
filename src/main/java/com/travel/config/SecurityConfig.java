//package com.travel.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//@Configuration
//public class SecurityConfig {
//
//    
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//
//            // 🚨 DISABLE CSRF
//            .csrf(AbstractHttpConfigurer::disable)
//
//            // 🚨 ENABLE CORS
//            .cors(cors -> cors.configurationSource(request -> {
//
//                CorsConfiguration config = new CorsConfiguration();
//
//                config.setAllowedOrigins(
//                        List.of("http://localhost:5173")
//                );
//
//                config.setAllowedMethods(
//                        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                );
//
//                config.setAllowedHeaders(List.of("*"));
//
//                config.setAllowCredentials(true);
//
//                return config;
//            }))
//
//            // 🚨 VERY IMPORTANT
//            .sessionManagement(session ->
//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//
//            // 🚨 DISABLE DEFAULT LOGIN FORM
//            .formLogin(AbstractHttpConfigurer::disable)
//
//            // 🚨 DISABLE HTTP BASIC
//            .httpBasic(AbstractHttpConfigurer::disable)
//
//            // 🚨 AUTHORIZATION
//            .authorizeHttpRequests(auth -> auth
//
//                    // 🔥 ALLOW LOGIN
//                    .requestMatchers("/auth/**").permitAll()
//
//                    // everything else open temporarily
//                    .anyRequest().permitAll()
//            );
//
//        return http.build();
//    }
//
//    // PASSWORD ENCODER
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//
//



package com.travel.config;

import com.travel.util.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // AUTH
                        .requestMatchers("/api/auth/**", "/error").permitAll()

                        // ADMIN - explicit allow first (frontend hit)
                        .requestMatchers("/api/audit/logs", "/api/admin/audit").hasRole("ADMIN")
                        .requestMatchers("/api/audit/**", "/api/admin/**", "/admin/**", "/audit/**").hasRole("ADMIN")

                        // ROLE BASED
                        .requestMatchers("/api/manager/**", "/manager/**").hasRole("MANAGER")
                        .requestMatchers("/api/finance/**", "/finance/**").hasRole("FINANCE")
                        .requestMatchers("/api/employee/**", "/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
