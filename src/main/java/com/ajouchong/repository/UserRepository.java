package com.ajouchong.repository;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByU_email(String u_email);
    Optional<User> findByU_name(String u_name);
    Optional<User> findByU_major(String u_major);
    Optional<User> findByU_id(Long u_id);
    Optional<User> findByU_role(UserRole u_role);

    boolean existsByU_email(String u_email);
}
