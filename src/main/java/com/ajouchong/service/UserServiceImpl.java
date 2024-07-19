package com.ajouchong.service;

import com.ajouchong.entity.User;
import com.ajouchong.entity.UserRole;
import com.ajouchong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String u_name, String u_major, String u_pwd, UserRole u_role) {
        String encodedPassword = passwordEncoder.encode(u_pwd);
        User user = new User(u_name, u_major, encodedPassword, u_role);
        return userRepository.save(user);
    }

    public Optional<User> findUserByU_name(String u_name) {
        return userRepository.findByU_name(u_name);
    }

    public Optional<User> findUserByU_major(String u_major) {
        return userRepository.findByU_major(u_major);
    }
}
