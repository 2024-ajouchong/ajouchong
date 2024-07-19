package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequestDto;
import com.ajouchong.entity.User;

import java.util.Optional;

public interface UserService {
    User join(UserRegistrationRequestDto requestDto);   //회원 가입
    Optional<User> findUser(String u_name);
    Optional<User> getCurrentUser();//아이디로 회원 검색
}
