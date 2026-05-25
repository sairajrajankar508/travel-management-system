package com.travel.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.travel.entity.User;
import com.travel.enums.Role;
import com.travel.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByEmail("admin@test.com").isEmpty()) {

                User admin = new User();

                admin.setName("System Admin");
                admin.setEmail("admin@test.com");

                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRole(Role.ADMIN);
                admin.setDepartment("Administration");
                admin.setActive(true);

                userRepository.save(admin);

                System.out.println("Admin user created!");
            }
        };
    }
}