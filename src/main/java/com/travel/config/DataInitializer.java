//package com.travel.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.travel.entity.User;
//import com.travel.enums.Role;
//import com.travel.repository.UserRepository;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner initDatabase(
//            UserRepository userRepository,
//            PasswordEncoder passwordEncoder) {
//
//        return args -> {
//
//            if (userRepository.findByEmail("admin@test.com").isEmpty()) {
//
//                User admin = new User();
//
//                admin.setName("System Admin");
//                admin.setEmail("admin@test.com");
//
//                admin.setPassword(
//                        passwordEncoder.encode("admin123")
//                );
//
//                admin.setRole(Role.ADMIN);
//                admin.setDepartment("Administration");
//                admin.setActive(true);
//
//                userRepository.save(admin);
//
//                System.out.println("Admin user created!");
//            }
//        };
//    }
//}



package com.travel.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.travel.entity.User;
import com.travel.enums.Role;
import com.travel.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            String adminEmail = "admin@test.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {

                User admin = new User();
                admin.setName("System Admin");
                admin.setEmail(adminEmail);

                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRole(Role.ADMIN);
                admin.setDepartment("Administration");
                admin.setActive(true);

                userRepository.save(admin);

                log.info("Admin user created successfully!");
            }

        };
    }
}
