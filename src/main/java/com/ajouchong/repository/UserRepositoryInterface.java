package com.ajouchong.repository;

import com.ajouchong.entity.User;

import java.util.Optional;

public interface UserRepositoryInterface {
    void save(User user);
    Optional<User> findById(String id);
    Optional<User> findByName(String name);
}
