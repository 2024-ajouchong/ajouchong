package com.ajouchong.repository;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    @NonNull
    Optional<User> findById(@NonNull String id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String name);
    Optional<User> findByMajor(String major);
    Optional<User> findByRole(UserRole role);

    boolean existsByEmail(String email);
}
