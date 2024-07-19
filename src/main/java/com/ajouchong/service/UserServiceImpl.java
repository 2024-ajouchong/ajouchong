package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequest;
import com.ajouchong.entity.User;
import com.ajouchong.exception.DuplicateEmailException;
import com.ajouchong.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User join(UserRegistrationRequest requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException("이미 가입된 이메일 입니다: " + requestDto.getEmail());
        }

        User user = requestDto.toEntity();
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> getCurrentUser(){     // 현재 회원 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String username = authentication.getName();
        return userRepository.findByName(username);
    }

}
