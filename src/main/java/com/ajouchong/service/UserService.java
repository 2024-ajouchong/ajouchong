package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequestDto;
import com.ajouchong.dto.UserRegistrationResponseDto;
import com.ajouchong.entity.User;
import com.ajouchong.jwt.JwtTokenDto;

import java.util.Optional;

public interface UserService {
    UserRegistrationResponseDto save(UserRegistrationRequestDto requestDto);   //회원 가입
    Optional<User> findUser(String name);
    Optional<User> getCurrentUser();
    JwtTokenDto signIn(String username, String password);
}
