package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequestDto;
import com.ajouchong.entity.User;
import com.ajouchong.exception.DuplicateEmailException;
import com.ajouchong.jwt.JwtTokenDto;
import com.ajouchong.jwt.JwtTokenProvider;
import com.ajouchong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public User join(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException("이미 가입된 이메일 입니다: " + requestDto.getEmail());
        }

        User user = requestDto.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Optional<User> getCurrentUser(){     // 현재 회원 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public JwtTokenDto signIn(String username, String password) {
        // username + password 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);    // 인증 처리

        return jwtTokenProvider.generateToken(authentication);
    }

}
