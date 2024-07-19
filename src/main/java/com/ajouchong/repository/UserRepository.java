package com.ajouchong.repository;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByMajor(String major);
    Optional<User> findById(String id);
    Optional<User> findByRole(UserRole role);

    boolean existsByEmail(String email);
}
