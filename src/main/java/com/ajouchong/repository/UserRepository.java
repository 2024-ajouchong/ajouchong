package com.ajouchong.repository;

import com.ajouchong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByU_name(String u_name);
    Optional<User> findByU_major(String u_major);
}
