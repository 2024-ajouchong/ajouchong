package com.ajouchong.service;

import com.ajouchong.dto.UserRegistrationRequest;
import com.ajouchong.entity.User;

import java.util.Optional;

public interface UserService {
    User join(UserRegistrationRequest requestDto);   //회원 가입
    Optional<User> findUser(String u_name);  //아이디로 회원 검색
}
