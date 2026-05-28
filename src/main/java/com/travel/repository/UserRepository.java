package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailIgnoreCase(String email);

}
